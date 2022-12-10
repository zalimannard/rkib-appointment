package ru.zalimannard.bachelorthesisserver.institution;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionMapper institutionMapper;
    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionMapper institutionMapper, InstitutionRepository institutionRepository) {
        this.institutionMapper = institutionMapper;
        this.institutionRepository = institutionRepository;
    }

    @Override
    public InstitutionDto read(int id) {
        Optional<Institution> institutionOptional = institutionRepository.findById(id);
        if (institutionOptional.isPresent()) {
            Institution institution = institutionOptional.get();
            return institutionMapper.toDto(institution);
        } else {
            throw new NotFoundException("Учреждение с id=" + id + " не найдено");
        }
    }

    @Override
    public List<InstitutionDto> list() {
        Iterable<Institution> institutions = institutionRepository.findAll();
        List<InstitutionDto> institutionDtos = new ArrayList<>();
        institutions.forEach(institution -> institutionDtos.add(institutionMapper.toDto(institution)));
        return institutionDtos;
    }

    @Override
    public InstitutionDto create(InstitutionDto institutionDto) {
        Institution institution = institutionMapper.toEntity(institutionDto);
        Institution addedInstitution = institutionRepository.save(institution);
        return institutionMapper.toDto(addedInstitution);
    }

    @Override
    public InstitutionDto update(InstitutionDto institutionDto) {
        if (institutionRepository.existsById(institutionDto.getId())) {
            Institution institution = institutionMapper.toEntity(institutionDto);
            institutionRepository.save(institution);
            return read(institutionDto.getId());
        } else {
            throw new NotFoundException("Учреждение с id=" + institutionDto.getId()+ " не найдено. Ничего не изменено.");
        }
    }

    @Override
    public InstitutionDto delete(int id) {
        InstitutionDto institutionDto = read(id);
        institutionRepository.deleteById(id);
        return institutionDto;
    }
}
