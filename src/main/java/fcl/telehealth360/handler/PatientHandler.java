package fcl.telehealth360.handler;

import fcl.telehealth360.model.Patient;
import fcl.telehealth360.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PatientHandler {
  @Autowired
  private PatientService patientService;

  public Mono<ServerResponse> loadAllPatients(ServerRequest serverRequest){
    Flux<Patient> patientFlux = patientService.getAllPatients();
    return ServerResponse.ok().body(patientFlux, Patient.class);
  }
}
