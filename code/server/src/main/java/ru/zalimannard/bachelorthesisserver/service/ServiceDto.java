package ru.zalimannard.bachelorthesisserver.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;
}
