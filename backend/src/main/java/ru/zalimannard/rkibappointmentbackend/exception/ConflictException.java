package ru.zalimannard.rkibappointmentbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.zalimannard.rkibappointmentbackend.exception.response.ExceptionMessage;
import ru.zalimannard.rkibappointmentbackend.exception.response.HttpCodes;

import java.util.List;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends BaseException {

    public ConflictException(String code, String message, String details) {
        super(HttpCodes.CONFLICT,
                code,
                List.of(ExceptionMessage.builder()
                        .code(code)
                        .message(message)
                        .details(details)
                        .build()));
    }

}