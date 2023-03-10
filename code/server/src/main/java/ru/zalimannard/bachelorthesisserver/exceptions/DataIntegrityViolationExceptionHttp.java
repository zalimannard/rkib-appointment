package ru.zalimannard.bachelorthesisserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataIntegrityViolationExceptionHttp extends RuntimeException {

    public DataIntegrityViolationExceptionHttp(String name) {
        super("Data integrity violation. Problem with the message" + name);
    }

}