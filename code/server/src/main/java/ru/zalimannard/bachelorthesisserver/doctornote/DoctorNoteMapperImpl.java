package ru.zalimannard.bachelorthesisserver.doctornote;

import org.springframework.stereotype.Component;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.institution.Institution;
import ru.zalimannard.bachelorthesisserver.institution.InstitutionRepository;

import java.util.Optional;

@Component
public class DoctorNoteMapperImpl implements DoctorNoteMapper {
    private final InstitutionRepository institutionRepository;

    public DoctorNoteMapperImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public DoctorNote toEntity(DoctorNoteDto dto) {
        return DoctorNote.builder()
                .id(dto.getId())
                .institution(obtainInstitution(dto.getInstitutionId()))
                .diagnosis(dto.getDiagnosis())
                .build();
    }

    @Override
    public DoctorNoteDto toDto(DoctorNote entity) {
        return DoctorNoteDto.builder()
                .id(entity.getId())
                .institutionId(obtainInstitutionId(entity.institution))
                .diagnosis(entity.diagnosis)
                .build();
    }

    @Override
    public int obtainInstitutionId(Institution institution) {
        return institution.getId();
    }

    @Override
    public Institution obtainInstitution(int institutionId) {
        Optional<Institution> institutionOptional = institutionRepository.findById(institutionId);
        if (institutionOptional.isPresent()) {
            return institutionOptional.get();
        } else {
            throw new NotFoundException("Вложенного в направление учреждения не существует");
        }
    }
}
