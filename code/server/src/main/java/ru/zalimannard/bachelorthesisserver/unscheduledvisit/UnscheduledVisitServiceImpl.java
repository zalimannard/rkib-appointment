package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnscheduledVisitServiceImpl implements UnscheduledVisitService {
    private final UnscheduledVisitMapper unscheduledVisitMapper;
    private final UnscheduledVisitRepository unscheduledVisitRepository;

    public UnscheduledVisitServiceImpl(UnscheduledVisitMapper unscheduledVisitMapper, UnscheduledVisitRepository unscheduledVisitRepository) {
        this.unscheduledVisitMapper = unscheduledVisitMapper;
        this.unscheduledVisitRepository = unscheduledVisitRepository;
    }

    @Override
    public UnscheduledVisitDto read(int id) {
        Optional<UnscheduledVisit> unscheduledVisitOptional = unscheduledVisitRepository.findById(id);
        if (unscheduledVisitOptional.isPresent()) {
            UnscheduledVisit unscheduledVisit = unscheduledVisitOptional.get();
            return unscheduledVisitMapper.toDto(unscheduledVisit);
        } else {
            throw new NotFoundException("Внеплановое посещение с id=" + id + " не найдено.");
        }
    }

    @Override
    public List<UnscheduledVisitDto> list() {
        Iterable<UnscheduledVisit> unscheduledVisits = unscheduledVisitRepository.findAll();
        List<UnscheduledVisitDto> unscheduledVisitDtos = new ArrayList<>();
        unscheduledVisits.forEach(unscheduledVisit -> unscheduledVisitDtos.add(unscheduledVisitMapper.toDto(unscheduledVisit)));
        return unscheduledVisitDtos;
    }

    @Override
    public UnscheduledVisitDto create(UnscheduledVisitDto unscheduledVisitDto) {
        UnscheduledVisit unscheduledVisit = unscheduledVisitMapper.toEntity(unscheduledVisitDto);
        UnscheduledVisit addedUnscheduledVisit = unscheduledVisitRepository.save(unscheduledVisit);
        return unscheduledVisitMapper.toDto(addedUnscheduledVisit);
    }

    @Override
    public UnscheduledVisitDto update(UnscheduledVisitDto unscheduledVisitDto) {
        if (unscheduledVisitRepository.existsById(unscheduledVisitDto.getId())) {
            UnscheduledVisit unscheduledVisit = unscheduledVisitMapper.toEntity(unscheduledVisitDto);
            unscheduledVisitRepository.save(unscheduledVisit);
            return read(unscheduledVisit.getId());
        } else {
            throw new NotFoundException("Внеплановое посещение с id=" + unscheduledVisitDto.getId() + " не найдено. Ничего не изменено.");
        }
    }

    @Override
    public UnscheduledVisitDto delete(int id) {
        UnscheduledVisitDto unscheduledVisitDto = read(id);
        unscheduledVisitRepository.deleteById(id);
        return unscheduledVisitDto;
    }
}
