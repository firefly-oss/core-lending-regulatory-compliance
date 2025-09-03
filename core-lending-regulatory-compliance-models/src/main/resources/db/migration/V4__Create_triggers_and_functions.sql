-- Function to automatically update the updated_at timestamp
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Create triggers for automatic updated_at timestamp updates

-- Reporting tables triggers
CREATE TRIGGER trigger_reporting_run_updated_at
    BEFORE UPDATE ON reporting_run
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER trigger_reporting_record_updated_at
    BEFORE UPDATE ON reporting_record
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER trigger_regulatory_submission_updated_at
    BEFORE UPDATE ON regulatory_submission
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- AML tables triggers
CREATE TRIGGER trigger_aml_case_updated_at
    BEFORE UPDATE ON aml_case
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER trigger_aml_action_updated_at
    BEFORE UPDATE ON aml_action
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER trigger_aml_sar_updated_at
    BEFORE UPDATE ON aml_sar
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- Function to validate AML case status transitions
CREATE OR REPLACE FUNCTION validate_aml_case_status_transition()
RETURNS TRIGGER AS $$
BEGIN
    -- Allow any transition if this is an INSERT
    IF TG_OP = 'INSERT' THEN
        RETURN NEW;
    END IF;
    
    -- For UPDATEs, validate status transitions
    -- CLOSED cases can only be reopened to INVESTIGATING
    IF OLD.aml_case_status = 'CLOSED' AND NEW.aml_case_status NOT IN ('CLOSED', 'INVESTIGATING') THEN
        RAISE EXCEPTION 'Invalid status transition: CLOSED cases can only be reopened to INVESTIGATING status';
    END IF;
    
    -- ESCALATED cases cannot be moved back to OPEN
    IF OLD.aml_case_status = 'ESCALATED' AND NEW.aml_case_status = 'OPEN' THEN
        RAISE EXCEPTION 'Invalid status transition: ESCALATED cases cannot be moved back to OPEN status';
    END IF;
    
    -- Automatically set case_closed_at when status becomes CLOSED
    IF NEW.aml_case_status = 'CLOSED' AND OLD.aml_case_status != 'CLOSED' THEN
        NEW.case_closed_at = CURRENT_DATE;
    END IF;
    
    -- Clear case_closed_at when reopening a case
    IF NEW.aml_case_status != 'CLOSED' AND OLD.aml_case_status = 'CLOSED' THEN
        NEW.case_closed_at = NULL;
    END IF;
    
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Create trigger for AML case status validation
CREATE TRIGGER trigger_aml_case_status_validation
    BEFORE INSERT OR UPDATE ON aml_case
    FOR EACH ROW
    EXECUTE FUNCTION validate_aml_case_status_transition();
