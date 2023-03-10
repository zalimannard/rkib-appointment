package ru.zalimannard.bachelorthesisserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class NotModifiedExceptionHttp extends RuntimeException {

    public NotModifiedExceptionHttp(String name) {
        super("The element from the " + name + " has not been changed");
    }

}
