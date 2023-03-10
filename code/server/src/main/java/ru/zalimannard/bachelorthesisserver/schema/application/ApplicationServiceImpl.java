package ru.zalimannard.bachelorthesisserver.schema.application;

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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    @Override
    public ApplicationDto create(ApplicationDto applicationDto) {
        try {
            Application applicationRequest = applicationMapper.toEntity(applicationDto, MappingType.DEFAULT);
            Application applicationResponse = applicationRepository.save(applicationRequest);
            return applicationMapper.toDto(applicationResponse);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.application}");
        }
    }

    @Override
    public ApplicationDto read(String id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHttp("${application.entityNames.application}", id));
        return applicationMapper.toDto(application);
    }

    @Override
    public List<ApplicationDto> search(ApplicationDto filterApplicationDto, int pageNo, int pageSize, String[] sort) {
        Application filterApplication = applicationMapper.toEntity(filterApplicationDto, MappingType.FORCE);
        List<Sort.Order> orders = Utils.ordersByStringArray(sort);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orders));

        List<Application> applicationList = applicationRepository.search(filterApplication.getPatient(),
                filterApplication.getSendingInstitution(), filterApplication.getStatus(),
                filterApplication.getDoctorNote(), filterApplication.getFinalDiagnosis(),
                filterApplication.getCommentary(), pageable);
        return applicationMapper.toDtoList(applicationList);
    }

    @Override
    public ApplicationDto update(String id, ApplicationDto applicationDto) {
        read(id);
        Application applicationRequest = applicationMapper.toEntity(applicationDto, MappingType.DEFAULT);
        applicationRequest.setId(id);
        Application applicationResponse = applicationRepository.save(applicationRequest);
        return applicationMapper.toDto(applicationResponse);
    }

    @Override
    public ApplicationDto delete(String id) {
        try {
            ApplicationDto applicationDto = read(id);
            applicationRepository.deleteById(id);
            return applicationDto;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.application}");
        }
    }

}
