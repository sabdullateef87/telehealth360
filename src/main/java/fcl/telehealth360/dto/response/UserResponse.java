package fcl.telehealth360.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserResponse {
    private String name;
    private String email;
    private String mobileNumber;
    private int age;
}