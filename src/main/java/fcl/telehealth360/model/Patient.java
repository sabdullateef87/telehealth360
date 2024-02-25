package fcl.telehealth360.model;

import java.security.SecureRandom;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Patient {
  private String patientId;
  private String name;
  private int age;
  private String mobileNumber;
  private String emailAddress;
  private String patientType; // first time or returning
  private byte[] patientCard;
  
  public static String generatePatientId() {
        String companyLabel = "TH/LG/";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
                        sb.append(characters.charAt(randomIndex));
        }
        return companyLabel.concat(sb.toString());
    }
}


// patient -> medical history [one / many]

// if patient is a first time patient, get other necessary details or run
// general test else, if the patient is RETURNING use medical history.
