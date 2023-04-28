package ru.zalimannard.rkibappointmentbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.zalimannard.rkibappointmentbackend.exception.response.ExceptionMessage;
import ru.zalimannard.rkibappointmentbackend.exception.response.HttpCodes;

import java.util.List;

@AllArgsConstructor
@Getter
public abstract class BaseException extends RuntimeException {

    private final HttpCodes httpCode;

    private final String code;

    private final List<ExceptionMessage> errors;

}
