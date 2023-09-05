package net.exsorce.webpanel.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse extends AbstractResponse {

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private int code;

}
