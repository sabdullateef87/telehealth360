package fcl.telehealth360.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import fcl.telehealth360.dto.response.UserResponse;
import fcl.telehealth360.model.Patient;

public class CustomMapper {

    public static class PatientMapper implements RowMapper<Patient>{
        @Override
        @Nullable
        public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Patient.builder()
                    .age(rs.getInt("age"))
                    .emailAddress(rs.getString("email_address"))
                    .mobileNumber(rs.getString("mobile_number"))
                    .build();
        }
    }


}
