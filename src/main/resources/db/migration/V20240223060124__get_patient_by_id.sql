DELIMITER //
CREATE PROCEDURE get_patient_by_email(
    IN p_email_address VARCHAR(100)
)
BEGIN
    SELECT * FROM patients WHERE email_address = p_email_address;
END//
DELIMITER ;