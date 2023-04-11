package ru.zalimannard.rkibappointmentbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonService;

@Component
@RequiredArgsConstructor
public class UserSecurity {

    private final PersonService personService;

    public boolean hasUserId(Authentication authentication, String userId) {
        String username = authentication.getName();
        Person personAuthed = personService.readEntityByUsername(username);
        return userId.equals(personAuthed.getId());
    }

}
