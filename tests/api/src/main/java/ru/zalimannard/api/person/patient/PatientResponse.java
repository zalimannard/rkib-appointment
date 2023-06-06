package ru.zalimannard.api.person.patient;

import lombok.Builder;
import lombok.Value;
import ru.zalimannard.api.person.PersonResponse;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class PatientResponse {

    String id;

    PersonResponse person;

    String phoneNumber;

    Date birthdate;

    String address;

    String occupation;

}
