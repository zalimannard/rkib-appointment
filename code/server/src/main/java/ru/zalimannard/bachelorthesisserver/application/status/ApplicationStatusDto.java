package ru.zalimannard.bachelorthesisserver.application.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationStatusDto {
    @NotNull
    @JsonProperty("id")
    private Integer id;

    @NotNull
    @JsonProperty("type")
    private ApplicationStatusType type;

    @NotNull
    @JsonProperty("name")
    private String name;
}
