package ru.zalimannard.rkibappointmentbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.zalimannard.rkibappointmentbackend.exception.response.ExceptionMessage;
import ru.zalimannard.rkibappointmentbackend.exception.response.HttpCodes;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    public BadRequestException(String code, String message, String details) {
        super(HttpCodes.BAD_REQUEST,
                code,
                List.of(ExceptionMessage.builder()
                        .code(code)
                        .message(message)
                        .details(details)
                        .build()));
    }

}