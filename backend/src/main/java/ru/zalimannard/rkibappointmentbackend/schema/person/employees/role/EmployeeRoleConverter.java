package ru.zalimannard.rkibappointmentbackend.schema.person.employees.role;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class EmployeeRoleConverter implements AttributeConverter<EmployeeRole, String> {

    @Override
    public String convertToDatabaseColumn(EmployeeRole attribute) {
        return attribute.getCode();
    }

    @Override
    public EmployeeRole convertToEntityAttribute(String dbData) {
        return Stream.of(EmployeeRole.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
