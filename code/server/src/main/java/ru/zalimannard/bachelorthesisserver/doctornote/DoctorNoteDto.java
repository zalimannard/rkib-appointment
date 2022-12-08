package ru.zalimannard.bachelorthesisserver.doctornote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zalimannard.bachelorthesisserver.institution.InstitutionDto;
import ru.zalimannard.bachelorthesisserver.institution.InstitutionService;

@Data
@Builder
public class DoctorNoteDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("institutionId")
    private Integer institutionId;
    @JsonProperty("diagnosis")
    private String diagnosis;

    public DoctorNote toEntity() {
        return DoctorNote.builder()
                .id(id)
                .diagnosis(diagnosis)
                .build();
    }
}
