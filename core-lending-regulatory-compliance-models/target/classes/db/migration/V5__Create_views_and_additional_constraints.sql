-- Create useful views for reporting and analytics

-- View for active AML cases with latest action
CREATE VIEW v_active_aml_cases AS
SELECT 
    ac.aml_case_id,
    ac.customer_id,
    ac.transaction_id,
    ac.aml_case_status,
    ac.case_opened_at,
    ac.risk_level,
    ac.notes,
    ac.created_at,
    ac.updated_at,
    la.latest_action_date,
    la.latest_action_type,
    la.latest_outcome
FROM aml_case ac
LEFT JOIN (
    SELECT DISTINCT ON (aml_case_id)
        aml_case_id,
        action_date as latest_action_date,
        action_type as latest_action_type,
        outcome as latest_outcome
    FROM aml_action
    ORDER BY aml_case_id, action_date DESC
) la ON ac.aml_case_id = la.aml_case_id
WHERE ac.aml_case_status IN ('OPEN', 'INVESTIGATING', 'ESCALATED');

-- View for reporting run summary
CREATE VIEW v_reporting_run_summary AS
SELECT 
    rr.reporting_run_id,
    rr.report_type,
    rr.reporting_period_start,
    rr.reporting_period_end,
    rr.run_status,
    rr.created_at,
    rr.updated_at,
    COUNT(rec.reporting_record_id) as total_records,
    COUNT(CASE WHEN rec.is_included = true THEN 1 END) as included_records,
    COUNT(CASE WHEN rec.is_included = false THEN 1 END) as excluded_records,
    SUM(CASE WHEN rec.is_included = true THEN rec.reported_amount ELSE 0 END) as total_included_amount,
    COUNT(rs.regulatory_submission_id) as submission_count
FROM reporting_run rr
LEFT JOIN reporting_record rec ON rr.reporting_run_id = rec.reporting_run_id
LEFT JOIN regulatory_submission rs ON rr.reporting_run_id = rs.reporting_run_id
GROUP BY rr.reporting_run_id, rr.report_type, rr.reporting_period_start, 
         rr.reporting_period_end, rr.run_status, rr.created_at, rr.updated_at;

-- View for AML case metrics by risk level
CREATE VIEW v_aml_risk_metrics AS
SELECT
    risk_level,
    aml_case_status,
    COUNT(*) as case_count,
    AVG(COALESCE(case_closed_at, CURRENT_DATE) - case_opened_at) as avg_days_open,
    MIN(case_opened_at) as earliest_case_date,
    MAX(case_opened_at) as latest_case_date
FROM aml_case
GROUP BY risk_level, aml_case_status;

-- Additional constraints for data integrity

-- Ensure reporting period end is after start
ALTER TABLE reporting_run 
ADD CONSTRAINT chk_reporting_run_period 
CHECK (reporting_period_end >= reporting_period_start);

-- Ensure submission date is reasonable (not too far in the future)
ALTER TABLE regulatory_submission 
ADD CONSTRAINT chk_regulatory_submission_date 
CHECK (submission_date <= CURRENT_DATE + INTERVAL '1 day');

-- Ensure SAR date is reasonable
ALTER TABLE aml_sar 
ADD CONSTRAINT chk_aml_sar_date 
CHECK (sar_date <= CURRENT_DATE + INTERVAL '1 day');

-- Ensure action date is reasonable
ALTER TABLE aml_action 
ADD CONSTRAINT chk_aml_action_date 
CHECK (action_date <= CURRENT_TIMESTAMP + INTERVAL '1 hour');

-- Ensure case opened date is reasonable
ALTER TABLE aml_case 
ADD CONSTRAINT chk_aml_case_opened_date 
CHECK (case_opened_at <= CURRENT_DATE + INTERVAL '1 day');

-- Create composite indexes for common query patterns
CREATE INDEX idx_reporting_record_composite_filter 
ON reporting_record(reporting_run_id, record_type, is_included);

CREATE INDEX idx_aml_case_composite_filter 
ON aml_case(aml_case_status, risk_level, case_opened_at);

CREATE INDEX idx_aml_action_composite_filter 
ON aml_action(aml_case_id, action_date, action_type);

-- Grant permissions (adjust as needed for your environment)
-- These are examples - modify according to your security requirements
-- GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO regulatory_compliance_app;
-- GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO regulatory_compliance_app;
-- GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA public TO regulatory_compliance_app;
