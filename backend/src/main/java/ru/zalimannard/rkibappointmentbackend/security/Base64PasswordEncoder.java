package ru.zalimannard.rkibappointmentbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonRepository;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class Base64PasswordEncoder implements PasswordEncoder {

    private final PersonRepository personRepository;

    @Override
    public String encode(CharSequence rawPassword) {
        return Base64.getEncoder().encodeToString(rawPassword.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        Person person = personRepository.getPersonByPassword(encodedPassword);
        if (person != null) {
            String anotherEncodedPassword = encode(person.getUsername() + ":" + rawPassword);
            return anotherEncodedPassword.equals(encodedPassword);
        } else {
            return false;
        }
    }

}
