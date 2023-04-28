package ru.zalimannard.api.person.patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class PatientRequest {

    @JsonProperty("personId")
    String personId;

    @JsonProperty("phoneNumber")
    String phoneNumber;

    @JsonProperty("birthdate")
    Date birthdate;

    @JsonProperty("address")
    String address;

    @JsonProperty("occupation")
    String occupation;

}
