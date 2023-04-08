package ru.zalimannard.rkibappointmentbackend.schema.person.gender;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PersonGenderConverter implements AttributeConverter<PersonGender, String> {

    @Override
    public String convertToDatabaseColumn(PersonGender attribute) {
        return attribute.getCode();
    }

    @Override
    public PersonGender convertToEntityAttribute(String dbData) {
        return Stream.of(PersonGender.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
