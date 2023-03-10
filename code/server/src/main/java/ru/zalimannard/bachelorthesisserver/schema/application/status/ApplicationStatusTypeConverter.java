package ru.zalimannard.bachelorthesisserver.schema.application.status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ApplicationStatusTypeConverter implements AttributeConverter<ApplicationStatusType, String> {

    @Override
    public String convertToDatabaseColumn(ApplicationStatusType attribute) {
        return attribute.getCode();
    }

    @Override
    public ApplicationStatusType convertToEntityAttribute(String dbData) {
        return Stream.of(ApplicationStatusType.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
