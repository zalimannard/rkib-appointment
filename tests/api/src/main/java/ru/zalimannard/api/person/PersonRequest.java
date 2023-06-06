package ru.zalimannard.api.person;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PersonRequest {

    String username;

    String password;

    String email;

    String lastName;

    String firstName;

    String patronymic;

}
