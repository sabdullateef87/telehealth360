package fcl.telehealth360.service;


import fcl.telehealth360.dto.request.CreatePatientRequest;
import fcl.telehealth360.model.Patient;
import reactor.core.publisher.Mono;


public interface IPatient {
    Mono<Patient> createNewPatient(CreatePatientRequest createPatientRequest);
    
}
