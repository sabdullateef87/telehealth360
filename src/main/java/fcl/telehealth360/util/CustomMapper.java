package fcl.telehealth360.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import fcl.telehealth360.dto.response.UserResponse;

public class CustomMapper  implements RowMapper<UserResponse>{

    @Override
    @Nullable
    public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {

        return UserResponse.builder()
            .age(rs.getInt("age"))
            .email(rs.getString("email_address"))
            .mobileNumber(rs.getString("mobile_number"))
            .build();

    }
    
}
