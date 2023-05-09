package ru.zalimannard.api.person;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class AuthGenerator {

    public String personToAut(PersonRequest personRequest) {
        return Base64.getEncoder().encodeToString(
                (personRequest.getUsername() + ":" + personRequest.getPassword()).getBytes()
        );
    }

}
