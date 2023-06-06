package ru.zalimannard.api.errors;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ErrorPartResponse {

    String code;

    String message;

    String details;

}
