package ru.zalimannard.rkibappointmentbackend.schema.procedures.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ProcedureResponseDto {

    @JsonProperty("id")
    String id;

    @JsonProperty("name")
    String name;

}
