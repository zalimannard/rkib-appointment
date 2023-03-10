package ru.zalimannard.bachelorthesisserver.schema.application.status;

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
public class ApplicationStatusServiceImpl implements ApplicationStatusService {

    private final ApplicationStatusRepository applicationStatusRepository;
    private final ApplicationStatusMapper applicationStatusMapper;

    @Override
    public ApplicationStatusDto create(ApplicationStatusDto applicationStatusDto) {
        try {
            ApplicationStatus applicationStatusRequest = applicationStatusMapper.toEntity(applicationStatusDto, MappingType.DEFAULT);
            ApplicationStatus applicationStatusResponse = applicationStatusRepository.save(applicationStatusRequest);
            return applicationStatusMapper.toDto(applicationStatusResponse);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.applicationStatus}");
        }
    }

    @Override
    public ApplicationStatusDto read(String id) {
        ApplicationStatus applicationStatus = applicationStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHttp("${application.entityNames.applicationStatus}", id));
        return applicationStatusMapper.toDto(applicationStatus);
    }

    @Override
    public List<ApplicationStatusDto> search(ApplicationStatusDto filterApplicationStatusDto, int pageNo, int pageSize, String[] sort) {
        ApplicationStatus filterApplicationStatus = applicationStatusMapper.toEntity(filterApplicationStatusDto, MappingType.FORCE);
        List<Sort.Order> orders = Utils.ordersByStringArray(sort);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orders));

        List<ApplicationStatus> applicationStatusList =
                applicationStatusRepository.search(filterApplicationStatus.getType(), filterApplicationStatus.getName(),
                        pageable);
        return applicationStatusMapper.toDtoList(applicationStatusList);
    }

    @Override
    public ApplicationStatusDto update(String id, ApplicationStatusDto applicationStatusDto) {
        read(id);
        ApplicationStatus applicationStatusRequest = applicationStatusMapper.toEntity(applicationStatusDto, MappingType.DEFAULT);
        applicationStatusRequest.setId(id);
        ApplicationStatus applicationStatusResponse = applicationStatusRepository.save(applicationStatusRequest);
        return applicationStatusMapper.toDto(applicationStatusResponse);
    }

    @Override
    public ApplicationStatusDto delete(String id) {
        try {
            ApplicationStatusDto applicationStatusDto = read(id);
            applicationStatusRepository.deleteById(id);
            return applicationStatusDto;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationExceptionHttp("${application.entityNames.applicationStatus}");
        }
    }

}
