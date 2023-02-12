package ru.zalimannard.bachelorthesisserver.application;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper = Mappers.getMapper(ApplicationMapper.class);

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public ApplicationDto get(int id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Application", "id", String.valueOf(id)));
        return applicationMapper.toDto(application);
    }

    @Override
    public List<ApplicationDto> list() {
        List<Application> applicationList = new ArrayList<>();
        applicationRepository.findAll().forEach(application -> applicationList.add(application));
        return applicationMapper.toDtoList(applicationList);
    }

    @Override
    public ApplicationDto create(ApplicationDto applicationDto) {
        Application applicationRequest = applicationMapper.toEntity(applicationDto);
        Application applicationResponse = applicationRepository.save(applicationRequest);
        return applicationMapper.toDto(applicationResponse);
    }

    @Override
    public ApplicationDto update(ApplicationDto applicationDto) {
        Application applicationRequest = applicationMapper.toEntity(applicationDto);
        if (applicationRepository.existsById(applicationRequest.getId())) {
            Application applicationResponse = applicationRepository.save(applicationRequest);
            return applicationMapper.toDto(applicationResponse);
        } else {
            throw new NotFoundException("Application", "id", String.valueOf(applicationRequest.getId()));
        }
    }

    @Override
    public ApplicationDto delete(int id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Application", "id", String.valueOf(id)));
        applicationRepository.deleteById(id);
        return applicationMapper.toDto(application);
    }
}
