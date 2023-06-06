package ru.zalimannard.api.errors;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class ErrorResponse {

    String httpCode;

    List<ErrorPartResponse> errors;

}