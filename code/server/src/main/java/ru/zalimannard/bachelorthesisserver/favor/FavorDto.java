package ru.zalimannard.bachelorthesisserver.favor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FavorDto {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

}
