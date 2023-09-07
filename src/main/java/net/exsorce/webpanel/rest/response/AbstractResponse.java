package net.exsorce.webpanel.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractResponse {

    @JsonProperty("responseType")
    private String responseType = getClass().getSimpleName();

}
