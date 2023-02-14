package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.application.ApplicationRepository;
import ru.zalimannard.bachelorthesisserver.doctor.DoctorRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.favor.FavorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnscheduledVisitServiceImpl implements UnscheduledVisitService {
    private final UnscheduledVisitRepository unscheduledVisitRepository;
    private final DoctorRepository doctorRepository;
    private final FavorRepository favorRepository;
    private final ApplicationRepository applicationRepository;
    private final UnscheduledVisitMapper unscheduledVisitMapper = Mappers.getMapper(UnscheduledVisitMapper.class);

    @Override
    public UnscheduledVisitDto get(String id) {
        UnscheduledVisit unscheduledVisit = unscheduledVisitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UnscheduledVisit", "id", id));
        return unscheduledVisitMapper.toDto(unscheduledVisit);
    }

    @Override
    public List<UnscheduledVisitDto> list() {
        List<UnscheduledVisit> unscheduledVisitList = new ArrayList<>();
        unscheduledVisitRepository.findAll().forEach(unscheduledVisitList::add);
        return unscheduledVisitMapper.toDtoList(unscheduledVisitList);
    }

    @Override
    public UnscheduledVisitDto create(UnscheduledVisitDto unscheduledVisitDto) {
        UnscheduledVisit unscheduledVisitRequest = unscheduledVisitMapper.toEntity(unscheduledVisitDto,
                doctorRepository, favorRepository, applicationRepository);
        UnscheduledVisit unscheduledVisitResponse = unscheduledVisitRepository.save(unscheduledVisitRequest);
        return unscheduledVisitMapper.toDto(unscheduledVisitResponse);
    }

    @Override
    public UnscheduledVisitDto update(UnscheduledVisitDto unscheduledVisitDto) {
        UnscheduledVisit unscheduledVisitRequest = unscheduledVisitMapper.toEntity(unscheduledVisitDto,
                doctorRepository, favorRepository, applicationRepository);
        if (unscheduledVisitRepository.existsById(unscheduledVisitRequest.getId())) {
            UnscheduledVisit unscheduledVisitResponse = unscheduledVisitRepository.save(unscheduledVisitRequest);
            return unscheduledVisitMapper.toDto(unscheduledVisitResponse);
        } else {
            throw new NotFoundException("UnscheduledVisit", "id", String.valueOf(unscheduledVisitRequest.getId()));
        }
    }

    @Override
    public UnscheduledVisitDto delete(String id) {
        UnscheduledVisit unscheduledVisit = unscheduledVisitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UnscheduledVisit", "id", id));
        unscheduledVisitRepository.deleteById(id);
        return unscheduledVisitMapper.toDto(unscheduledVisit);
    }
}
