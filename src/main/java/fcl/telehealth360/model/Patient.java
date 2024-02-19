package fcl.telehealth360.model;

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
}

// patient -> medical history [one / many]

// if patient is a first time patient, get other necessary details or run
// general test else, if the patient is RETURNING use medical history.
