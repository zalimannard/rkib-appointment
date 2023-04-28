package ru.zalimannard.api.person.staffmember;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class StaffMemberResponse {

    @JsonProperty("id")
    String id;

    @JsonProperty("personId")
    String personId;

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

    @JsonProperty("roles")
    List<String> roles;

}
