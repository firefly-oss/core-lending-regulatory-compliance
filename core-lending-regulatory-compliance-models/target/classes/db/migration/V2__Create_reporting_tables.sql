-- Create reporting_run table
CREATE TABLE reporting_run (
    reporting_run_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    report_type report_type_enum NOT NULL,
    reporting_period_start DATE NOT NULL,
    reporting_period_end DATE NOT NULL,
    run_status run_status_enum NOT NULL DEFAULT 'OPEN',
    remarks TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create reporting_record table
CREATE TABLE reporting_record (
    reporting_record_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    reporting_run_id UUID NOT NULL,
    loan_servicing_case_id UUID,
    customer_id UUID,
    transaction_id UUID,
    record_type record_type_enum NOT NULL,
    reported_amount DECIMAL(19,4),
    currency_code currency_code_enum,
    additional_data TEXT,
    is_included BOOLEAN NOT NULL DEFAULT true,
    exclusion_reason TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    -- Foreign key constraint
    CONSTRAINT fk_reporting_record_reporting_run 
        FOREIGN KEY (reporting_run_id) 
        REFERENCES reporting_run(reporting_run_id) 
        ON DELETE CASCADE
);

-- Create regulatory_submission table
CREATE TABLE regulatory_submission (
    regulatory_submission_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    reporting_run_id UUID NOT NULL,
    submission_ref VARCHAR(255) NOT NULL,
    submission_date DATE NOT NULL,
    submission_status submission_status_enum NOT NULL DEFAULT 'SUBMITTED',
    response_details TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    -- Foreign key constraint
    CONSTRAINT fk_regulatory_submission_reporting_run 
        FOREIGN KEY (reporting_run_id) 
        REFERENCES reporting_run(reporting_run_id) 
        ON DELETE CASCADE,
        
    -- Unique constraint on submission reference
    CONSTRAINT uk_regulatory_submission_ref UNIQUE (submission_ref)
);

-- Create indexes for better performance
CREATE INDEX idx_reporting_record_reporting_run_id ON reporting_record(reporting_run_id);
CREATE INDEX idx_reporting_record_customer_id ON reporting_record(customer_id);
CREATE INDEX idx_reporting_record_transaction_id ON reporting_record(transaction_id);
CREATE INDEX idx_reporting_record_loan_servicing_case_id ON reporting_record(loan_servicing_case_id);
CREATE INDEX idx_reporting_record_record_type ON reporting_record(record_type);
CREATE INDEX idx_reporting_record_created_at ON reporting_record(created_at);

CREATE INDEX idx_regulatory_submission_reporting_run_id ON regulatory_submission(reporting_run_id);
CREATE INDEX idx_regulatory_submission_submission_date ON regulatory_submission(submission_date);
CREATE INDEX idx_regulatory_submission_status ON regulatory_submission(submission_status);

CREATE INDEX idx_reporting_run_report_type ON reporting_run(report_type);
CREATE INDEX idx_reporting_run_status ON reporting_run(run_status);
CREATE INDEX idx_reporting_run_period ON reporting_run(reporting_period_start, reporting_period_end);
CREATE INDEX idx_reporting_run_created_at ON reporting_run(created_at);
