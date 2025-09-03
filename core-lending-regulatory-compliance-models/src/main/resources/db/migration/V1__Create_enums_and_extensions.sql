-- Enable UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create AML enums
CREATE TYPE aml_case_status_enum AS ENUM (
    'OPEN',
    'INVESTIGATING', 
    'REPORTED',
    'CLOSED',
    'ESCALATED'
);

CREATE TYPE risk_level_enum AS ENUM (
    'LOW',
    'MEDIUM',
    'HIGH',
    'CRITICAL'
);

CREATE TYPE aml_action_type_enum AS ENUM (
    'REVIEW',
    'CUSTOMER_CONTACT',
    'REPORT_TO_AUTHORITY',
    'ACCOUNT_FREEZE',
    'DOCUMENT_REQUEST'
);

CREATE TYPE sar_status_enum AS ENUM (
    'DRAFT',
    'SUBMITTED',
    'FEEDBACK_RECEIVED',
    'CLOSED',
    'AMENDED'
);

-- Create Reporting enums
CREATE TYPE report_type_enum AS ENUM (
    'CIRBE',
    'FINREP',
    'AML',
    'COREP',
    'SEPBLAC'
);

CREATE TYPE run_status_enum AS ENUM (
    'OPEN',
    'IN_PROGRESS',
    'COMPLETED',
    'FAILED',
    'CANCELLED'
);

CREATE TYPE submission_status_enum AS ENUM (
    'SUBMITTED',
    'ACCEPTED',
    'REJECTED',
    'REVISED',
    'SUPERSEDED'
);

CREATE TYPE record_type_enum AS ENUM (
    'EXPOSURE',
    'DELINQUENCY',
    'AML_TXN',
    'COLLATERAL',
    'PROVISION'
);

CREATE TYPE currency_code_enum AS ENUM (
    'EUR',
    'USD',
    'GBP',
    'CHF'
);
