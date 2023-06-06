package ru.zalimannard.api.procedure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ProcedureRequest {

    String name;

    @JsonCreator
    public ProcedureRequest(@JsonProperty("name") String name) {
        this.name = name != null ? name.trim() : null;
    }

}
