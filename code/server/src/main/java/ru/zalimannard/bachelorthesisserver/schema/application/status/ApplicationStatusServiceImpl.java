package ru.zalimannard.bachelorthesisserver.schema.application.status;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationStatusServiceImpl implements ApplicationStatusService {
    private final ApplicationStatusRepository applicationStatusRepository;
    private final ApplicationStatusMapper applicationStatusMapper = Mappers.getMapper(ApplicationStatusMapper.class);

    @Override
    public ApplicationStatusDto get(String id) {
        ApplicationStatus applicationStatus = applicationStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ApplicationStatus", "id", id));
        return applicationStatusMapper.toDto(applicationStatus);
    }

    @Override
    public List<ApplicationStatusDto> list(ApplicationStatusDto exampleApplicationStatusDto) {
        ApplicationStatus exampleApplicationStatus = applicationStatusMapper.toEntity(exampleApplicationStatusDto);
        List<ApplicationStatus> applicationStatusList = new ArrayList<>(applicationStatusRepository.findAll(Example.of(exampleApplicationStatus)));
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
    public ApplicationStatusDto delete(String id) {
        ApplicationStatus applicationStatus = applicationStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ApplicationStatus", "id", id));
        applicationStatusRepository.deleteById(id);
        return applicationStatusMapper.toDto(applicationStatus);
    }
}
