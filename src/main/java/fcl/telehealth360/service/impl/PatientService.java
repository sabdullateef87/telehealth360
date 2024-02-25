package fcl.telehealth360.service.impl;

import java.util.Objects;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fcl.telehealth360.dto.request.CreatePatientRequest;
import fcl.telehealth360.dto.response.UserResponse;
import fcl.telehealth360.model.Patient;
import fcl.telehealth360.repository.PatientRepository;
import fcl.telehealth360.service.IPatient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PatientService implements IPatient {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Mono<Patient> createNewPatient(CreatePatientRequest createPatientRequest) {
        return this.patientRepository.getPatientByEmail(createPatientRequest.getEmailAddress())
                .switchIfEmpty(Mono.defer(() -> this.patientRepository.createNewPatient(createPatientRequest)))
                .flatMap(existingPatient -> {
                    if(existingPatient != null) throw new DuplicateKeyException("User with this email already exist");
                    return Mono.just(Patient.builder().build());
                })
                .onErrorMap(throwable -> {
                    return throwable;
                });
    }


}
