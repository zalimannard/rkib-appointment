package ru.zalimannard.rkibappointmentbackend.schema.person.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonService;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto.PatientResponseDto;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientMapper mapper;
    private final PatientRepository repository;

    private final PersonService personService;

    @Override
    public PatientResponseDto create(PatientRequestDto patientDto) {
        Person person = personService.readEntity(patientDto.getPersonId());
        Patient patientToCreate = mapper.toEntity(patientDto, person);
        Patient createdPatient = createEntity(patientToCreate);
        return mapper.toDto(createdPatient);
    }

    @Override
    public Patient createEntity(Patient patient) {
        try {
            return repository.save(patient);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("pas-01", "Конфликт при добавлении Patient в базу данных", e.getMessage());
        }
    }

    @Override
    public PatientResponseDto read(String id) {
        Patient patient = readEntity(id);
        return mapper.toDto(patient);
    }

    @Override
    public Patient readEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("pas-02", "Не найден Patient с id=" + id, null));
    }

    @Override
    public PatientResponseDto update(String id, PatientRequestDto patientDto) {
        Person person = personService.readEntity(patientDto.getPersonId());
        Patient patientToUpdate = mapper.toEntity(patientDto, person);
        patientToUpdate.setId(id);
        Patient updatedPatient = updateEntity(patientToUpdate);
        return mapper.toDto(updatedPatient);
    }

    @Override
    public Patient updateEntity(Patient patient) {
        try {
            return repository.save(patient);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("pas-03", "Конфликт при обновлении Patient в базе данных", e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Patient patient = readEntity(id);
            repository.delete(patient);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("pas-04", "Конфликт при удалении Patient из базы данных", e.getMessage());
        }
    }
}