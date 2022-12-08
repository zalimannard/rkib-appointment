package ru.zalimannard.bachelorthesisserver.institution;

import java.util.List;

public interface InstitutionService {
    InstitutionDto get(int id);

    List<InstitutionDto> getAll();

    InstitutionDto post(InstitutionDto institutionDto);

    InstitutionDto put(InstitutionDto institutionDto);

    InstitutionDto delete(int id);
}
