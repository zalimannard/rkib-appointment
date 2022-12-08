package ru.zalimannard.bachelorthesisserver.doctornote;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.institution.InstitutionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorNoteServiceImpl implements DoctorNoteService {
    private final DoctorNoteRepository doctorNoteRepository;
    private final InstitutionRepository institutionRepository;

    public DoctorNoteServiceImpl(DoctorNoteRepository doctorNoteRepository,
                                 InstitutionRepository institutionRepository) {
        this.doctorNoteRepository = doctorNoteRepository;
        this.institutionRepository = institutionRepository;
    }

    @Override
    public DoctorNoteDto get(int id) {
        Optional<DoctorNote> doctorNoteOptional = doctorNoteRepository.findById(id);
        if (doctorNoteOptional.isPresent()) {
            DoctorNote doctorNote = doctorNoteOptional.get();
            return doctorNote.toDto();
        } else {
            throw new NotFoundException("Учреждение не найдено");
        }
    }

    @Override
    public List<DoctorNoteDto> getAll() {
        Iterable<DoctorNote> doctorNoteEntities = doctorNoteRepository.findAll();
        List<DoctorNoteDto> doctorNoteDtos = new ArrayList<>();
        doctorNoteEntities.forEach(institutionEntity -> doctorNoteDtos.add(institutionEntity.toDto()));
        return doctorNoteDtos;
    }

    @Override
    public DoctorNoteDto post(DoctorNoteDto doctorNoteDto) {
        System.out.println(") " + doctorNoteDto);
        DoctorNote doctorNoteToAdd = DoctorNote.builder()
                .diagnosis(doctorNoteDto.getDiagnosis())
                .institution(institutionRepository.findById(doctorNoteDto.getInstitutionId()).get())
                .build();

        System.out.println("* " + doctorNoteToAdd);
        DoctorNote doctorNoteCreated = doctorNoteRepository.save(doctorNoteToAdd);
        System.out.println("& " + doctorNoteCreated);
        return doctorNoteCreated.toDto();
    }

    @Override
    public DoctorNoteDto put(DoctorNoteDto doctorNoteDto) {
        if (doctorNoteRepository.existsById(doctorNoteDto.getId())) {
            DoctorNote doctorNote = doctorNoteDto.toEntity();
            doctorNoteRepository.save(doctorNote);
            return get(doctorNoteDto.getId());
        } else {
            throw new NotFoundException("Изменяемого учреждения не существует");
        }
    }

    @Override
    public DoctorNoteDto delete(int id) {
        DoctorNoteDto doctorNoteDto = get(id);
        doctorNoteRepository.deleteById(id);
        return doctorNoteDto;
    }
}
