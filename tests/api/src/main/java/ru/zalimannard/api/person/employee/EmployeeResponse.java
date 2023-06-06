package ru.zalimannard.api.person.employee;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class EmployeeResponse {

    String id;

    String personId;

    String username;

    String password;

    String lastName;

    String firstName;

    String patronymic;

    List<String> roles;

}
