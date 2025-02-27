-- V1 - CREATE ENUMS FOR REGULATORY & COMPLIANCE REPORTING

-- 1) reporting_run -> report_type
CREATE TYPE report_type AS ENUM (
    'CIRBE',
    'FINREP',
    'AML',
    'COREP',
    'SEPBLAC'
);

-- 2) reporting_run -> run_status
CREATE TYPE run_status AS ENUM (
    'OPEN',
    'IN_PROGRESS',
    'COMPLETED',
    'FAILED',
    'CANCELLED'
);

-- 3) reporting_record -> record_type
CREATE TYPE record_type AS ENUM (
    'EXPOSURE',
    'DELINQUENCY',
    'AML_TXN',
    'COLLATERAL',
    'PROVISION'
);

-- 4) reporting_record -> currency_code
CREATE TYPE currency_code AS ENUM (
    'EUR',
    'USD',
    'GBP',
    'CHF'
);

-- 5) regulatory_submission -> submission_status
CREATE TYPE submission_status AS ENUM (
    'SUBMITTED',
    'ACCEPTED',
    'REJECTED',
    'REVISED',
    'SUPERSEDED'
);

-- 6) aml_case -> aml_case_status
CREATE TYPE aml_case_status AS ENUM (
    'OPEN',
    'INVESTIGATING',
    'REPORTED',
    'CLOSED',
    'ESCALATED'
);

-- 7) aml_case -> risk_level
CREATE TYPE risk_level AS ENUM (
    'LOW',
    'MEDIUM',
    'HIGH',
    'CRITICAL'
);

-- 8) aml_action -> action_type
CREATE TYPE aml_action_type AS ENUM (
    'REVIEW',
    'CUSTOMER_CONTACT',
    'REPORT_TO_AUTHORITY',
    'ACCOUNT_FREEZE',
    'DOCUMENT_REQUEST'
);

-- 9) aml_sar -> sar_status
CREATE TYPE sar_status AS ENUM (
    'DRAFT',
    'SUBMITTED',
    'FEEDBACK_RECEIVED',
    'CLOSED',
    'AMENDED'
);
