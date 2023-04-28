package ru.zalimannard.rkibappointmentbackend.schema.appointment.status.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class AppointmentStatusTypeConverter implements AttributeConverter<AppointmentStatusType, String> {

    @Override
    public String convertToDatabaseColumn(AppointmentStatusType attribute) {
        return attribute.getCode();
    }

    @Override
    public AppointmentStatusType convertToEntityAttribute(String dbData) {
        return Stream.of(AppointmentStatusType.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
