package ru.zalimannard.bachelorthesisserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    // TODO: Удалить после переработки всех сущносстей
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String name, String field, String value) {
        super(name + " with " + field + "=" + value + " not found");
    }

}
