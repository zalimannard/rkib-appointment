package ru.zalimannard.rkibappointmentbackend.schema.person.role;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PersonRoleConverter implements AttributeConverter<PersonRole, String> {

    @Override
    public String convertToDatabaseColumn(PersonRole attribute) {
        return attribute.getCode();
    }

    @Override
    public PersonRole convertToEntityAttribute(String dbData) {
        return Stream.of(PersonRole.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
