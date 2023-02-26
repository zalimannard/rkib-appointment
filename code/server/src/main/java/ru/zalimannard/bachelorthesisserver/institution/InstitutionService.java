package ru.zalimannard.bachelorthesisserver.institution;

import java.util.List;

public interface InstitutionService {

    InstitutionDto get(String id);

    List<InstitutionDto> list(InstitutionDto exampleInstitutionDto);

    InstitutionDto create(InstitutionDto institutionDto);

    InstitutionDto update(InstitutionDto institutionDto);

    InstitutionDto delete(String id);
}
