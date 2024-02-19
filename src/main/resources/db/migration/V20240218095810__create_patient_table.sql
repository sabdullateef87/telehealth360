CREATE TABLE IF NOT EXISTS patients (
    patient_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL,
    mobile_number VARCHAR(15) NOT NULL UNIQUE,
    email_address VARCHAR(100) UNIQUE,
    patient_type VARCHAR(20)
);