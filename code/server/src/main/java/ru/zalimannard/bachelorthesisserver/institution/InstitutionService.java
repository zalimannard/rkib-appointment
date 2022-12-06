package ru.zalimannard.bachelorthesisserver.institution;

import java.util.List;

public interface InstitutionService {
    InstitutionDto get(int id, boolean expand);

    List<InstitutionDto> getAll(boolean expand);

    InstitutionDto post(InstitutionEntity institutionEntity);

    InstitutionDto put(InstitutionEntity institutionEntity);

    InstitutionDto delete(int id);
}
