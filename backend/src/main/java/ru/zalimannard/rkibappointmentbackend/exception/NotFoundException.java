package ru.zalimannard.rkibappointmentbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.zalimannard.rkibappointmentbackend.exception.response.ExceptionMessage;
import ru.zalimannard.rkibappointmentbackend.exception.response.HttpCodes;

import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {

    public NotFoundException(String code, String field, String value) {
        super(HttpCodes.NOT_FOUND,
                code,
                "Объект не найден",
                List.of(ExceptionMessage.builder()
                        .field(field)
                        .value(value).build()));
    }

}