package ru.zalimannard.rkibappointmentbackend.schema.institution.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class InstitutionResponseDto {

    @JsonProperty("id")
    String id;

    @JsonProperty("name")
    String name;

}
