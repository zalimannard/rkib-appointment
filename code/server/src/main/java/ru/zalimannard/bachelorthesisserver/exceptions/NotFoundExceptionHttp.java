package ru.zalimannard.bachelorthesisserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptionHttp extends RuntimeException {

    // TODO: Удалить после переработки всех сущносстей
    public NotFoundExceptionHttp(String message) {
        super(message);
    }

    public NotFoundExceptionHttp(String name, String field, String value) {
        super(name + " with " + field + "=" + value + " not found");
    }

    public NotFoundExceptionHttp(String name, String id) {
        super(name + " with id=" + id + " not found");
    }

}
