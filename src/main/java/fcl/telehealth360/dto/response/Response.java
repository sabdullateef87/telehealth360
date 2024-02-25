package fcl.telehealth360.dto.response;


import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

@Builder
@Getter
@Setter
public class Response {
    private Date timeStamp;
    private String responseMessage;
    private String responseCode;
    private Object data;
    private List<String> errors;
}
