package fcl.telehealth360.repository;

import java.util.List;
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
import fcl.telehealth360.util.CustomMapper;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Repository
@Transactional
public class PatientRepository {
    Logger logger = LoggerFactory.getLogger(PatientRepository.class.getSimpleName());
    protected static final String SINGLE_RESULT = "single";
    protected static final String MULTIPLE_RESULT = "list";
    protected static final String LIST_COUNT = "count";
    private SimpleJdbcCall createPatient;

    @Autowired
    public PatientRepository(JdbcTemplate jdbcTemplate) {
        createPatient = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("insert_patient")
                .returningResultSet(SINGLE_RESULT, new CustomMapper());
    }

    public Mono<UserResponse> createNewPatient(CreatePatientRequest createPatientRequest) {
        try {
            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("p_name", createPatientRequest.getName())
                    .addValue("p_age", createPatientRequest.getAge())
                    .addValue("p_email_address", createPatientRequest.getEmailAddress())
                    .addValue("p_patient_type", "NEW")
                    .addValue("p_patient_card", "")
                    .addValue("p_mobile_number", createPatientRequest.getMobileNumber());

            return this.async(() -> this.createPatient.execute(in)).map(resultSet -> {
                List<UserResponse> result = (List<UserResponse>) resultSet.get(SINGLE_RESULT);
                UserResponse userResponse = result.get(0);
                return userResponse;

            }).doOnError(error -> {
                logger.error(error.getMessage());
                throw new DatabaseException(error.getMessage());
            });

        } catch (Exception e) {
            logger.error(e.getMessage());
            return Mono.error(e);
        }
    }

    private <T> Mono<T> async(Callable<T> callable) {
        return Mono.fromCallable(callable).subscribeOn(Schedulers.boundedElastic());
    }
}
