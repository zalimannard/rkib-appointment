package ru.zalimannard.api.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PersonRequest {

    @JsonProperty("username")
    String username;

    @JsonProperty("password")
    String password;

    @JsonProperty("lastName")
    String lastName;

    @JsonProperty("firstName")
    String firstName;

    @JsonProperty("patronymic")
    String patronymic;

}
