package ru.zalimannard.bachelorthesisserver.schema.institution;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface InstitutionService {

    InstitutionDto create(@Valid InstitutionDto institutionDto);

    InstitutionDto read(String id);

    List<InstitutionDto> search(InstitutionDto filterInstitutionDto, int pageNo, int pageSize, String[] sortBy);

    InstitutionDto update(String id, @Valid InstitutionDto institutionDto);

    InstitutionDto delete(String id);

}
