package ru.zalimannard.bachelorthesisserver.application;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationMapper applicationMapper, ApplicationRepository applicationRepository) {
        this.applicationMapper = applicationMapper;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public ApplicationDto read(int id) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        if (applicationOptional.isPresent()) {
            Application application = applicationOptional.get();
            return applicationMapper.toDto(application);
        } else {
            throw new NotFoundException("Обращение с id=" + id + " не найдено.");
        }
    }

    @Override
    public List<ApplicationDto> list() {
        Iterable<Application> applications = applicationRepository.findAll();
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        applications.forEach(application -> applicationDtos.add(applicationMapper.toDto(application)));
        return applicationDtos;
    }

    @Override
    public ApplicationDto create(ApplicationDto applicationDto) {
        Application application = applicationMapper.toEntity(applicationDto);
        Application addedApplication = applicationRepository.save(application);
        return applicationMapper.toDto(addedApplication);
    }

    @Override
    public ApplicationDto update(ApplicationDto applicationDto) {
        if (applicationRepository.existsById(applicationDto.getId())) {
            Application application = applicationMapper.toEntity(applicationDto);
            applicationRepository.save(application);
            return read(application.getId());
        } else {
            throw new NotFoundException("Обращение с id=" + applicationDto.getId() + " не найдено. Ничего не изменено.");
        }
    }

    @Override
    public ApplicationDto delete(int id) {
        ApplicationDto applicationDto = read(id);
        applicationRepository.deleteById(id);
        return applicationDto;
    }
}
