package ru.zalimannard.api.person.patient;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class PatientRequest {

    String personId;

    String phoneNumber;

    Date birthdate;

    String address;

    String occupation;

}
