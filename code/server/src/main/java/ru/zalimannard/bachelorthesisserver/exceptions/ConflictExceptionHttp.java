package ru.zalimannard.bachelorthesisserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictExceptionHttp extends RuntimeException {

    public ConflictExceptionHttp(String message) {
        super(message);
    }

}
