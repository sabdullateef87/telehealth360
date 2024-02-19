package fcl.telehealth360.service;

import fcl.telehealth360.model.Patient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PatientService {

  public Flux<Patient> getAllPatients( ) {
    return Flux.range(1,20)
        .map(i -> Patient.builder().name("patient " + i).build());
  }
}
