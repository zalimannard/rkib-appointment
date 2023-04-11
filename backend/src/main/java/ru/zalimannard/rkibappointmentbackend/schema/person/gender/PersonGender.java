package ru.zalimannard.rkibappointmentbackend.schema.person.gender;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PersonGender {

    MALE("M"),
    FEMALE("F");

    @Getter
    private final String code;

}