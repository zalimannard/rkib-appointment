package ru.zalimannard.bachelorthesisserver.schema.application.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApplicationStatusDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private ApplicationStatusType type;

    @JsonProperty("name")
    private String name;

}
