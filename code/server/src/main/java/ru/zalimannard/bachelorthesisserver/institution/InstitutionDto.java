package ru.zalimannard.bachelorthesisserver.institution;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class InstitutionDto {
    @JsonProperty("id")
    private int id;
    @NotNull
    @JsonProperty("name")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("doctorNotes")
    private List<String> doctorNotes;

    public InstitutionEntity toEntity() {
        InstitutionEntity entity = new InstitutionEntity();
        entity.setId(id);
        entity.setName(name);
        return entity;
    }
}
