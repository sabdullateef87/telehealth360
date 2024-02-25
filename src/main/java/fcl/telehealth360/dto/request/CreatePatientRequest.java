package fcl.telehealth360.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class CreatePatientRequest {
    private String name;
    private int age;
    private String mobileNumber;
    private String emailAddress;
    private String patientType;
}
