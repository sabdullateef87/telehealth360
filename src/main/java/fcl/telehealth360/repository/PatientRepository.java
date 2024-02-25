package fcl.telehealth360.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fcl.telehealth360.dto.request.CreatePatientRequest;
import fcl.telehealth360.dto.response.UserResponse;
import fcl.telehealth360.exception.DatabaseException;
import fcl.telehealth360.model.Patient;
import fcl.telehealth360.util.CustomMapper;
import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Repository
@Transactional
public class PatientRepository {
    Logger logger = LoggerFactory.getLogger(PatientRepository.class.getSimpleName());
    protected static final String SINGLE_RESULT = "single";
    protected static final String MULTIPLE_RESULT = "list";
    protected static final String LIST_COUNT = "count";
    private SimpleJdbcCall createPatient, getPatient;

    @Autowired
    public PatientRepository(JdbcTemplate jdbcTemplate) {
        createPatient = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insert_patient")
                .returningResultSet(SINGLE_RESULT, new CustomMapper.PatientMapper());
        getPatient = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_patient_by_email")
                .returningResultSet(SINGLE_RESULT, new CustomMapper.PatientMapper());
    }

    public Mono<Patient> createNewPatient(CreatePatientRequest createPatientRequest) {
        logger.info("" + createPatientRequest);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_name", createPatientRequest.getName())
                .addValue("p_age", createPatientRequest.getAge())
                .addValue("p_email_address", createPatientRequest.getEmailAddress())
                .addValue("p_mobile_number", createPatientRequest.getMobileNumber())
                .addValue("p_patient_type", "NEW");
        return this.async(() -> this.createPatient.execute(in)).map(resultSet -> {
            List<Patient> result = (List<Patient>) resultSet.get(SINGLE_RESULT);
            Patient userResponse = result.get(0);
            return userResponse;
        }).onErrorMap(error -> {
            logger.error(error.getMessage());
            throw new DatabaseException(error.getMessage());
        });
    }

    public Mono<Patient> getPatientByEmail(String email) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_email_address", email);
        return Mono.fromCallable(() -> this.getPatient.execute(in))
            .flatMap(resultSet -> {
                List<Patient> result = (List<Patient>) resultSet.get(SINGLE_RESULT);
                if (result.isEmpty()) {
                    return Mono.empty(); // Return empty Mono when no patient is found
                } else {
                    return Mono.just(result.get(0));
                }
            });
    }


    private <T> Mono<T> async(Callable<T> callable) {
        return Mono.fromCallable(callable).subscribeOn(Schedulers.boundedElastic());
    }
}
