-- V3 - CASTS USING "WITH INOUT AS IMPLICIT" FOR ALL ENUM TYPES

-------------------------
-- report_type
-------------------------
CREATE CAST (varchar AS report_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- run_status
-------------------------
CREATE CAST (varchar AS run_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- record_type
-------------------------
CREATE CAST (varchar AS record_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- currency_code
-------------------------
CREATE CAST (varchar AS currency_code)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- submission_status
-------------------------
CREATE CAST (varchar AS submission_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- aml_case_status
-------------------------
CREATE CAST (varchar AS aml_case_status)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- risk_level
-------------------------
CREATE CAST (varchar AS risk_level)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- aml_action_type
-------------------------
CREATE CAST (varchar AS aml_action_type)
    WITH INOUT
    AS IMPLICIT;

-------------------------
-- sar_status
-------------------------
CREATE CAST (varchar AS sar_status)
    WITH INOUT
    AS IMPLICIT;
