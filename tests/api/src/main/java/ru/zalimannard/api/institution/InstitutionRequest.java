package ru.zalimannard.api.institution;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class InstitutionRequest {

    String name;

    @JsonCreator
    public InstitutionRequest(@JsonProperty("name") String name) {
        this.name = name != null ? name.trim() : null;
    }

}
