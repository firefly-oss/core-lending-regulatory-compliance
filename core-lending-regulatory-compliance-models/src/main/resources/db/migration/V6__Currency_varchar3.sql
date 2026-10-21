-- ISO currency is VARCHAR(3). Any 3-letter code can be stored.

ALTER TABLE reporting_record
    ALTER COLUMN currency_code TYPE VARCHAR(3) USING currency_code::text;

DROP TYPE IF EXISTS currency_code_enum;
