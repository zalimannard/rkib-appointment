package ru.zalimannard.bachelorthesisserver.schema.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Validated
public interface PatientService {

    PatientDto create(@Valid PatientDto patientDto);

    PatientDto read(String id);

    List<PatientDto> search(PatientDto filterPatientDto, @Past Date beginBirthdate, @Past Date endBirthdate, int pageNo,
                            int pageSize, String[] sortBy);

    PatientDto update(String id, @Valid PatientDto patientDto);

    PatientDto delete(String id);

}
