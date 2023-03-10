package ru.zalimannard.bachelorthesisserver.schema.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.DataIntegrityViolationExceptionHttp;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundExceptionHttp;
import ru.zalimannard.bachelorthesisserver.utils.Utils;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientDto create(PatientDto patientDto) {
        try {
            Patient patientRequest = patientMapper.toEntity(patientDto, MappingType.DEFAULT);
            Patient patientResponse = patientRepository.save(patientRequest);
            return patientMapper.toDto(patientResponse);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.patient}");
        }
    }

    @Override
    public PatientDto read(String id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHttp("${application.entityNames.patient}", id));
        return patientMapper.toDto(patient);
    }

    @Override
    public List<PatientDto> search(PatientDto filterPatientDto, Date beginBirthdate, Date endBirthdate,
                                   int pageNo, int pageSize, String[] sort) {
        Patient filterPatient = patientMapper.toEntity(filterPatientDto, MappingType.FORCE);
        List<Sort.Order> orders = Utils.ordersByStringArray(sort);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orders));

        List<Patient> patientList = patientRepository.search(beginBirthdate, endBirthdate,
                filterPatient.getLastName(), filterPatient.getFirstName(), filterPatient.getPatronymic(),
                filterPatient.getPhoneNumber(), filterPatient.getAddress(), filterPatient.getOccupation(), pageable);
        return patientMapper.toDtoList(patientList);
    }

    @Override
    public PatientDto update(String id, PatientDto patientDto) {
        read(id);
        try {
            Patient patientRequest = patientMapper.toEntity(patientDto, MappingType.DEFAULT);
            patientRequest.setId(id);
            Patient patientResponse = patientRepository.save(patientRequest);
            return patientMapper.toDto(patientResponse);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.patient}");
        }
    }

    @Override
    public PatientDto delete(String id) {
        try {
            PatientDto patientDto = read(id);
            patientRepository.deleteById(id);
            return patientDto;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.patient}");
        }
    }

}
