package ru.zalimannard.rkibappointmentbackend.exception;

import ru.zalimannard.rkibappointmentbackend.exception.response.ExceptionMessage;
import ru.zalimannard.rkibappointmentbackend.exception.response.HttpCodes;

import java.util.List;

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