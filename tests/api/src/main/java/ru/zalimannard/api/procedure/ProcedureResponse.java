package ru.zalimannard.api.procedure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ProcedureResponse {

    @JsonProperty("id")
    String id;

    @JsonProperty("name")
    String name;

}
