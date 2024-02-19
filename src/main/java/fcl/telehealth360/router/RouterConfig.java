package fcl.telehealth360.router;

import fcl.telehealth360.handler.PatientHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RouterConfig {

  @Autowired
  private PatientHandler patientHandler;

  @Bean
  public RouterFunction<ServerResponse> routerFunction(){
    return route()
        .GET("/patient", accept(APPLICATION_JSON), patientHandler::loadAllPatients).build();
  }
}
