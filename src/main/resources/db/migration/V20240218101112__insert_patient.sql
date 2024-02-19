DELIMITER //
CREATE PROCEDURE insert_patient(
    IN p_name VARCHAR(100),
    IN p_age INT,
    IN p_mobile_number VARCHAR(15),
    IN p_email_address VARCHAR(100),
    IN p_patient_type VARCHAR(20)
)
BEGIN

    INSERT INTO patients (name, age, mobile_number, email_address, patient_type)
    VALUES (p_name, p_age, p_mobile_number, p_email_address, p_patient_type);

    SELECT * FROM patients WHERE patient_id = LAST_INSERT_ID();


END//

DELIMITER ;
