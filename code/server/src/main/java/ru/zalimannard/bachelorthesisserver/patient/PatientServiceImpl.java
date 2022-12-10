package ru.zalimannard.bachelorthesisserver.patient;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientMapper patientMapper;
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientMapper patientMapper, PatientRepository patientRepository) {
        this.patientMapper = patientMapper;
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientDto read(int id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            return patientMapper.toDto(patient);
        } else {
            throw new NotFoundException("Пациент с id=" + id + " не найден");
        }
    }

    @Override
    public List<PatientDto> list() {
        Iterable<Patient> patients = patientRepository.findAll();
        List<PatientDto> patientDtos = new ArrayList<>();
        patients.forEach(patient -> patientDtos.add(patientMapper.toDto(patient)));
        return patientDtos;
    }

    @Override
    public PatientDto create(PatientDto patientDto) {
        Patient patient = patientMapper.toEntity(patientDto);
        Patient addedPatient = patientRepository.save(patient);
        return patientMapper.toDto(addedPatient);
    }

    @Override
    public PatientDto update(PatientDto patientDto) {
        if (patientRepository.existsById(patientDto.getId())) {
            Patient patient = patientMapper.toEntity(patientDto);
            patientRepository.save(patient);
            return read(patientDto.getId());
        } else {
            throw new NotFoundException("Пациент с id=" + patientDto.getId() + " не найден. Ничего не изменено");
        }
    }

    @Override
    public PatientDto delete(int id) {
        PatientDto patientDto = read(id);
        patientRepository.deleteById(id);
        return patientDto;
    }
}
