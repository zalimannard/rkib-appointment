package ru.zalimannard.rkibappointmentbackend.schema.institution.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class InstitutionRequestDto {

    @JsonProperty("name")
    @NotBlank(message = "Не указано название учреждения")
    String name;

    // У Jackson проблема с десериализацией классов с одним полем. Поэтому конкретный метод с указанием
    @JsonCreator
    public InstitutionRequestDto(@JsonProperty("name") String name) {
        this.name = name != null ? name.trim() : null;
    }

}
