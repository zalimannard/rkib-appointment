package ru.zalimannard.bachelorthesisserver.doctornote;

import org.mapstruct.*;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.institution.Institution;
import ru.zalimannard.bachelorthesisserver.institution.InstitutionRepository;

import java.util.List;

@Mapper
public interface DoctorNoteMapper {

    DoctorNote toEntity(DoctorNoteDto dto, InstitutionRepository institutionRepository);

    @Mapping(target = "institutionId", source = "entity.institution.id")
    DoctorNoteDto toDto(DoctorNote entity);

    List<DoctorNote> toEntityList(List<DoctorNoteDto> dtoList);

    List<DoctorNoteDto> toDtoList(List<DoctorNote> entityList);

    @AfterMapping
    default void toEntity(@MappingTarget DoctorNote entity, DoctorNoteDto dto, InstitutionRepository institutionRepository) {
        Institution institutionEntity = institutionRepository.findById(dto.getInstitutionId())
                .orElseThrow(() -> new NotFoundException("Institution", "id", dto.getInstitutionId()));
        entity.setInstitution(institutionEntity);
    }
}
