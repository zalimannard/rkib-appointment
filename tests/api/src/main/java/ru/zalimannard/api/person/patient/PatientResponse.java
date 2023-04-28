package ru.zalimannard.api.person.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import ru.zalimannard.api.person.PersonResponse;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class PatientResponse {

    @JsonProperty("id")
    String id;

    @JsonProperty("person")
    PersonResponse person;

    @JsonProperty("phoneNumber")
    String phoneNumber;

    @JsonProperty("birthdate")
    Date birthdate;

    @JsonProperty("address")
    String address;

    @JsonProperty("occupation")
    String occupation;

}
