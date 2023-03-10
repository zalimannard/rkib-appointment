package ru.zalimannard.bachelorthesisserver.schema.application.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.schema.application.status.type.ApplicationStatusType;

@Data
public class ApplicationStatusDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty("type")
    @NotNull
    private ApplicationStatusType type;

    @JsonProperty("name")
    @NotBlank
    private String name;

}
