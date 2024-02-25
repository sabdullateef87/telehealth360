package fcl.telehealth360.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fcl.telehealth360.dto.request.CreatePatientRequest;
import fcl.telehealth360.dto.response.Response;
import fcl.telehealth360.model.Patient;
import fcl.telehealth360.service.IPatient;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    private IPatient patientService;

    @PostMapping("/create")
    public Mono<ResponseEntity<Response>> createNewPatient(@RequestBody CreatePatientRequest patientRequest) throws Exception{
        return this.patientService.createNewPatient(patientRequest)
                .flatMap(patient -> {
                    Response response = Response.builder()
                            .timeStamp(new Date())
                            .responseMessage("Patient created successfully")
                            .responseCode("200")
                            .data(patient)
                            .build();
                    return Mono.just(ResponseEntity.status(HttpStatus.OK).body(response));
                });
    }
}
