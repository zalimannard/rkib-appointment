package ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.role;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StaffMemberRoleConverter implements AttributeConverter<StaffMemberRole, String> {

    @Override
    public String convertToDatabaseColumn(StaffMemberRole attribute) {
        return attribute.getCode();
    }

    @Override
    public StaffMemberRole convertToEntityAttribute(String dbData) {
        return Stream.of(StaffMemberRole.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
