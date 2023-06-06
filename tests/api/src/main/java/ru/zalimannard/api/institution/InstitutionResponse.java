package ru.zalimannard.api.institution;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class InstitutionResponse {

    @JsonProperty("id")
    String id;

    @JsonProperty("name")
    String name;

}
