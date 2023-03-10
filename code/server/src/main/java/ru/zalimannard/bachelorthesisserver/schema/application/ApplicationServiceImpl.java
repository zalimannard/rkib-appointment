package ru.zalimannard.bachelorthesisserver.schema.application;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.schema.application.status.ApplicationStatusRepository;
import ru.zalimannard.bachelorthesisserver.schema.doctornote.DoctorNoteRepository;
import ru.zalimannard.bachelorthesisserver.schema.patient.PatientRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final PatientRepository patientRepository;
    private final DoctorNoteRepository doctorNoteRepository;
    private final ApplicationStatusRepository applicationStatusRepository;
    private final ApplicationMapper applicationMapper = Mappers.getMapper(ApplicationMapper.class);

    @Override
    public ApplicationDto get(String id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Application", "id", id));
        return applicationMapper.toDto(application);
    }

    @Override
    public List<ApplicationDto> list(ApplicationDto exampleApplicationDto) {
        Application exampleApplication = applicationMapper.toEntity(exampleApplicationDto,
                applicationRepository, patientRepository, doctorNoteRepository, applicationStatusRepository);
        List<Application> applicationList = new ArrayList<>(applicationRepository.findAll(Example.of(exampleApplication)));
        return applicationMapper.toDtoList(applicationList);
    }

    @Override
    public ApplicationDto create(ApplicationDto applicationDto) {
        Application applicationRequest = applicationMapper.toEntity(applicationDto, applicationRepository,
                patientRepository, doctorNoteRepository, applicationStatusRepository);
        Application applicationResponse = applicationRepository.save(applicationRequest);
        return applicationMapper.toDto(applicationResponse);
    }

    @Override
    public ApplicationDto update(ApplicationDto applicationDto) {
        Application applicationRequest = applicationMapper.toEntity(applicationDto, applicationRepository,
                patientRepository, doctorNoteRepository, applicationStatusRepository);
        if (applicationRepository.existsById(applicationRequest.getId())) {
            Application applicationResponse = applicationRepository.save(applicationRequest);
            return applicationMapper.toDto(applicationResponse);
        } else {
            throw new NotFoundException("Application", "id", String.valueOf(applicationRequest.getId()));
        }
    }

    @Override
    public ApplicationDto delete(String id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Application", "id", id));
        applicationRepository.deleteById(id);
        return applicationMapper.toDto(application);
    }

}
