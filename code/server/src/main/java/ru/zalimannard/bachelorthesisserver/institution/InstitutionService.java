package ru.zalimannard.bachelorthesisserver.institution;

import java.util.List;

public interface InstitutionService {
    InstitutionDto read(int id);

    List<InstitutionDto> list();

    InstitutionDto create(InstitutionDto institutionDto);

    InstitutionDto update(InstitutionDto institutionDto);

    InstitutionDto delete(int id);
}
