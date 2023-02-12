package ru.zalimannard.bachelorthesisserver.application.status;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationStatusServiceImpl implements ApplicationStatusService {
    private final ApplicationStatusRepository applicationStatusRepository;
    private final ApplicationStatusMapper applicationStatusMapper = Mappers.getMapper(ApplicationStatusMapper.class);

    public ApplicationStatusServiceImpl(ApplicationStatusRepository applicationStatusRepository) {
        this.applicationStatusRepository = applicationStatusRepository;
    }

    @Override
    public ApplicationStatusDto get(int id) {
        ApplicationStatus applicationStatus = applicationStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ApplicationStatus", "id", String.valueOf(id)));
        return applicationStatusMapper.toDto(applicationStatus);
    }

    @Override
    public List<ApplicationStatusDto> list() {
        List<ApplicationStatus> applicationStatusList = new ArrayList<>();
        applicationStatusRepository.findAll().forEach(applicationStatus -> applicationStatusList.add(applicationStatus));
        return applicationStatusMapper.toDtoList(applicationStatusList);
    }

    @Override
    public ApplicationStatusDto create(ApplicationStatusDto applicationStatusDto) {
        ApplicationStatus applicationStatusRequest = applicationStatusMapper.toEntity(applicationStatusDto);
        ApplicationStatus applicationStatusResponse = applicationStatusRepository.save(applicationStatusRequest);
        return applicationStatusMapper.toDto(applicationStatusResponse);
    }

    @Override
    public ApplicationStatusDto update(ApplicationStatusDto applicationStatusDto) {
        ApplicationStatus applicationStatusRequest = applicationStatusMapper.toEntity(applicationStatusDto);
        if (applicationStatusRepository.existsById(applicationStatusRequest.getId())) {
            ApplicationStatus applicationStatusResponse = applicationStatusRepository.save(applicationStatusRequest);
            return applicationStatusMapper.toDto(applicationStatusResponse);
        } else {
            throw new NotFoundException("ApplicationStatus", "id", String.valueOf(applicationStatusRequest.getId()));
        }
    }

    @Override
    public ApplicationStatusDto delete(int id) {
        ApplicationStatus applicationStatus = applicationStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ApplicationStatus", "id", String.valueOf(id)));
        applicationStatusRepository.deleteById(id);
        return applicationStatusMapper.toDto(applicationStatus);
    }
}
