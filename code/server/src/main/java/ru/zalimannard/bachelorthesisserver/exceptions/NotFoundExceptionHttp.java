package ru.zalimannard.bachelorthesisserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptionHttp extends RuntimeException {

    public NotFoundExceptionHttp(String name, String id) {
        super(name + " with id=" + id + " not found");
    }

}
