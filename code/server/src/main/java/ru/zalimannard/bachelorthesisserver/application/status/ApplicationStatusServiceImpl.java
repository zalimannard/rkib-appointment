package ru.zalimannard.bachelorthesisserver.application.status;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationStatusServiceImpl implements ApplicationStatusService {
    private final ApplicationStatusMapper applicationStatusMapper;
    private final ApplicationStatusRepository applicationStatusRepository;

    public ApplicationStatusServiceImpl(ApplicationStatusMapper applicationStatusMapper, ApplicationStatusRepository applicationStatusRepository) {
        this.applicationStatusMapper = applicationStatusMapper;
        this.applicationStatusRepository = applicationStatusRepository;
    }

    @Override
    public ApplicationStatusDto get(int id) {
        Optional<ApplicationStatus> applicationStatusOptional = applicationStatusRepository.findById(id);
        if (applicationStatusOptional.isPresent()) {
            ApplicationStatus applicationStatus = applicationStatusOptional.get();
            return applicationStatusMapper.toDto(applicationStatus);
        } else {
            throw new NotFoundException("Статус обращения с id=" + id + " не найден");
        }
    }

    @Override
    public List<ApplicationStatusDto> getAll() {
        Iterable<ApplicationStatus> applicationStatuses = applicationStatusRepository.findAll();
        List<ApplicationStatusDto> applicationStatusDtos = new ArrayList<>();
        applicationStatuses.forEach(applicationStatus -> applicationStatusDtos.add(applicationStatusMapper.toDto(applicationStatus)));
        return applicationStatusDtos;
    }

    @Override
    public ApplicationStatusDto post(ApplicationStatusDto applicationStatusDto) {
        ApplicationStatus applicationStatus = applicationStatusMapper.toEntity(applicationStatusDto);
        ApplicationStatus addedApplicationStatus = applicationStatusRepository.save(applicationStatus);
        return applicationStatusMapper.toDto(addedApplicationStatus);
    }

    @Override
    public ApplicationStatusDto put(ApplicationStatusDto applicationStatusDto) {
        if (applicationStatusRepository.existsById(applicationStatusDto.getId())) {
            ApplicationStatus applicationStatus = applicationStatusMapper.toEntity(applicationStatusDto);
            applicationStatusRepository.save(applicationStatus);
            return get(applicationStatusDto.getId());
        } else {
            throw new NotFoundException("Статус обращения с id=" + applicationStatusDto.getId()+ " не найден. Ничего не изменено.");
        }
    }

    @Override
    public ApplicationStatusDto delete(int id) {
        ApplicationStatusDto applicationStatusDto = get(id);
        applicationStatusRepository.deleteById(id);
        return applicationStatusDto;
    }
}
