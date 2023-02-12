package ru.zalimannard.bachelorthesisserver.application.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApplicationStatusDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("type")
    private ApplicationStatusType type;

    @JsonProperty("name")
    private String name;
}
