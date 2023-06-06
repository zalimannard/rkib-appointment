package ru.zalimannard.api.person;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PersonResponse {

    String id;

    String username;

    String email;

    String lastName;

    String firstName;

    String patronymic;

}
