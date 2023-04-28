package ru.zalimannard.rkibappointmentbackend.exception.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ExceptionResponse {

    @JsonProperty("httpCode")
    private final String httpCode;

    @JsonProperty("errors")
    private final List<ExceptionMessage> errors;

}