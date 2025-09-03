-- Create aml_case table
CREATE TABLE aml_case (
    aml_case_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    reporting_run_id UUID,
    customer_id UUID NOT NULL,
    transaction_id UUID NOT NULL,
    aml_case_status aml_case_status_enum NOT NULL DEFAULT 'OPEN',
    case_opened_at DATE NOT NULL,
    case_closed_at DATE,
    risk_level risk_level_enum NOT NULL,
    notes TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    -- Foreign key constraint (optional reference to reporting_run)
    CONSTRAINT fk_aml_case_reporting_run 
        FOREIGN KEY (reporting_run_id) 
        REFERENCES reporting_run(reporting_run_id) 
        ON DELETE SET NULL,
        
    -- Check constraint to ensure case_closed_at is after case_opened_at
    CONSTRAINT chk_aml_case_dates 
        CHECK (case_closed_at IS NULL OR case_closed_at >= case_opened_at)
);

-- Create aml_action table
CREATE TABLE aml_action (
    aml_action_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    aml_case_id UUID NOT NULL,
    action_date TIMESTAMP WITH TIME ZONE NOT NULL,
    action_type aml_action_type_enum NOT NULL,
    outcome TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    -- Foreign key constraint
    CONSTRAINT fk_aml_action_aml_case 
        FOREIGN KEY (aml_case_id) 
        REFERENCES aml_case(aml_case_id) 
        ON DELETE CASCADE
);

-- Create aml_sar table (Suspicious Activity Report)
CREATE TABLE aml_sar (
    aml_sar_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    aml_case_id UUID NOT NULL,
    sar_reference VARCHAR(255) NOT NULL,
    sar_date DATE NOT NULL,
    sar_status sar_status_enum NOT NULL DEFAULT 'DRAFT',
    authority_feedback TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    -- Foreign key constraint
    CONSTRAINT fk_aml_sar_aml_case 
        FOREIGN KEY (aml_case_id) 
        REFERENCES aml_case(aml_case_id) 
        ON DELETE CASCADE,
        
    -- Unique constraint on SAR reference
    CONSTRAINT uk_aml_sar_reference UNIQUE (sar_reference)
);

-- Create indexes for better performance
CREATE INDEX idx_aml_case_reporting_run_id ON aml_case(reporting_run_id);
CREATE INDEX idx_aml_case_customer_id ON aml_case(customer_id);
CREATE INDEX idx_aml_case_transaction_id ON aml_case(transaction_id);
CREATE INDEX idx_aml_case_status ON aml_case(aml_case_status);
CREATE INDEX idx_aml_case_risk_level ON aml_case(risk_level);
CREATE INDEX idx_aml_case_opened_at ON aml_case(case_opened_at);
CREATE INDEX idx_aml_case_created_at ON aml_case(created_at);

CREATE INDEX idx_aml_action_aml_case_id ON aml_action(aml_case_id);
CREATE INDEX idx_aml_action_action_date ON aml_action(action_date);
CREATE INDEX idx_aml_action_action_type ON aml_action(action_type);
CREATE INDEX idx_aml_action_created_at ON aml_action(created_at);

CREATE INDEX idx_aml_sar_aml_case_id ON aml_sar(aml_case_id);
CREATE INDEX idx_aml_sar_sar_date ON aml_sar(sar_date);
CREATE INDEX idx_aml_sar_status ON aml_sar(sar_status);
CREATE INDEX idx_aml_sar_created_at ON aml_sar(created_at);
