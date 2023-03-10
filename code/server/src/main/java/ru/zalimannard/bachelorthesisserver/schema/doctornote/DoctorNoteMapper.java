package ru.zalimannard.bachelorthesisserver.schema.doctornote;

import org.mapstruct.*;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.schema.institution.Institution;
import ru.zalimannard.bachelorthesisserver.schema.institution.InstitutionRepository;

import java.util.List;

@Mapper
public interface DoctorNoteMapper {

    @Mapping(target = "institution", ignore = true)
    DoctorNote toEntity(DoctorNoteDto dto,
                        @Context InstitutionRepository institutionRepository);

    @Mapping(target = "institutionId", source = "entity.institution.id")
    DoctorNoteDto toDto(DoctorNote entity);

    List<DoctorNote> toEntityList(List<DoctorNoteDto> dtoList,
                                  @Context InstitutionRepository institutionRepository);

    List<DoctorNoteDto> toDtoList(List<DoctorNote> entityList);

    @AfterMapping
    default void toEntity(@MappingTarget DoctorNote entity, DoctorNoteDto dto,
                          InstitutionRepository institutionRepository) {
        if (dto.getInstitutionId() != null) {
            Institution institutionEntity = institutionRepository.findById(dto.getInstitutionId())
                    .orElseThrow(() -> new NotFoundException("Institution", "id", dto.getInstitutionId()));
            entity.setInstitution(institutionEntity);
        }
    }
}
