-- V2 - CREATE TABLES FOR REGULATORY & COMPLIANCE REPORTING

-- ========================================================================
-- TABLE: reporting_run
-- ========================================================================
CREATE TABLE IF NOT EXISTS reporting_run (
                                             reporting_run_id        BIGSERIAL PRIMARY KEY,
                                             report_type             report_type NOT NULL,       -- e.g. CIRBE, FINREP, AML, etc.
                                             reporting_period_start  DATE,
                                             reporting_period_end    DATE,
                                             run_status              run_status NOT NULL,        -- OPEN, IN_PROGRESS, etc.
                                             remarks                 TEXT,
                                             created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- ========================================================================
-- TABLE: reporting_record
-- ========================================================================
CREATE TABLE IF NOT EXISTS reporting_record (
                                                reporting_record_id     BIGSERIAL PRIMARY KEY,
                                                reporting_run_id        BIGINT NOT NULL,
                                                loan_servicing_case_id  BIGINT,   -- External reference (no FK)
                                                customer_id             BIGINT,   -- External reference (no FK)
                                                transaction_id          BIGINT,   -- External reference (no FK)
                                                record_type             record_type NOT NULL,  -- e.g. EXPOSURE, DELINQUENCY, etc.
                                                reported_amount         DECIMAL(18,2),
    currency_code           currency_code,
    additional_data         TEXT,    -- Could store JSON
    is_included             BOOLEAN DEFAULT TRUE,
    exclusion_reason        TEXT,
    created_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_record_run
    FOREIGN KEY (reporting_run_id)
    REFERENCES reporting_run (reporting_run_id)
    );

-- ========================================================================
-- TABLE: regulatory_submission
-- ========================================================================
CREATE TABLE IF NOT EXISTS regulatory_submission (
                                                     regulatory_submission_id  BIGSERIAL PRIMARY KEY,
                                                     reporting_run_id          BIGINT NOT NULL,
                                                     submission_ref            VARCHAR(100),       -- Official submission reference/ID
    submission_date           DATE,
    submission_status         submission_status NOT NULL,  -- e.g. SUBMITTED, ACCEPTED, etc.
    response_details          TEXT,
    created_at                TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_submission_run
    FOREIGN KEY (reporting_run_id)
    REFERENCES reporting_run (reporting_run_id)
    );

-- ========================================================================
-- TABLE: aml_case
-- ========================================================================
CREATE TABLE IF NOT EXISTS aml_case (
                                        aml_case_id       BIGSERIAL PRIMARY KEY,
                                        reporting_run_id  BIGINT,   -- Optional link to a run
                                        customer_id       BIGINT,   -- External reference to Customer
                                        transaction_id    BIGINT,   -- External reference if suspicious
                                        aml_case_status   aml_case_status NOT NULL,  -- e.g. OPEN, INVESTIGATING
                                        case_opened_at    DATE,
                                        case_closed_at    DATE,
                                        risk_level        risk_level,
                                        notes             TEXT,
                                        created_at        TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at        TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- ========================================================================
-- TABLE: aml_action
-- ========================================================================
CREATE TABLE IF NOT EXISTS aml_action (
                                          aml_action_id     BIGSERIAL PRIMARY KEY,
                                          aml_case_id       BIGINT NOT NULL,
                                          action_date       TIMESTAMP NOT NULL DEFAULT NOW(),
    action_type       aml_action_type NOT NULL,  -- e.g. REVIEW, CUSTOMER_CONTACT
    outcome           TEXT,  -- free-text or short code describing result
    created_at        TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at        TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_action_case
    FOREIGN KEY (aml_case_id)
    REFERENCES aml_case (aml_case_id)
    );

-- ========================================================================
-- TABLE: aml_sar
-- ========================================================================
CREATE TABLE IF NOT EXISTS aml_sar (
                                       aml_sar_id       BIGSERIAL PRIMARY KEY,
                                       aml_case_id      BIGINT NOT NULL,
                                       sar_reference    VARCHAR(100),  -- Reference code for the SAR
    sar_date         DATE,
    sar_status       sar_status NOT NULL,    -- e.g. DRAFT, SUBMITTED, CLOSED
    authority_feedback  TEXT,
    created_at       TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_sar_case
    FOREIGN KEY (aml_case_id)
    REFERENCES aml_case (aml_case_id)
    );
