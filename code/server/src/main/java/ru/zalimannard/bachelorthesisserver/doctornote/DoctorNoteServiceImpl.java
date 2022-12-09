package ru.zalimannard.bachelorthesisserver.doctornote;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.institution.InstitutionMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorNoteServiceImpl implements DoctorNoteService {
    private final DoctorNoteMapper doctorNoteMapper;
    private final DoctorNoteRepository doctorNoteRepository;

    public DoctorNoteServiceImpl(DoctorNoteMapper doctorNoteMapper, DoctorNoteRepository doctorNoteRepository) {
        this.doctorNoteMapper = doctorNoteMapper;
        this.doctorNoteRepository = doctorNoteRepository;
    }

    @Override
    public DoctorNoteDto get(int id) {
        Optional<DoctorNote> doctorNoteOptional = doctorNoteRepository.findById(id);
        if (doctorNoteOptional.isPresent()) {
            DoctorNote doctorNote = doctorNoteOptional.get();
            return doctorNoteMapper.toDto(doctorNote);
        } else {
            throw new NotFoundException("Направление с id=" + id + " не найдено.");
        }
    }

    @Override
    public List<DoctorNoteDto> getAll() {
        Iterable<DoctorNote> doctorNotes = doctorNoteRepository.findAll();
        List<DoctorNoteDto> doctorNoteDtos = new ArrayList<>();
        doctorNotes.forEach(doctorNote -> doctorNoteDtos.add(doctorNoteMapper.toDto(doctorNote)));
        return doctorNoteDtos;
    }

    @Override
    public DoctorNoteDto post(DoctorNoteDto doctorNoteDto) {
        DoctorNote doctorNoteToAdd = doctorNoteMapper.toEntity(doctorNoteDto);
        DoctorNote doctorNoteCreated = doctorNoteRepository.save(doctorNoteToAdd);
        return doctorNoteMapper.toDto(doctorNoteCreated);
    }

    @Override
    public DoctorNoteDto put(DoctorNoteDto doctorNoteDto) {
        if (doctorNoteRepository.existsById(doctorNoteDto.getId())) {
            DoctorNote doctorNote = doctorNoteMapper.toEntity(doctorNoteDto);
            doctorNoteRepository.save(doctorNote);
            return get(doctorNoteDto.getId());
        } else {
            throw new NotFoundException("Направление с id=" + doctorNoteDto.getId() + " не найдено. Ничего не изменено.");
        }
    }

    @Override
    public DoctorNoteDto delete(int id) {
        DoctorNoteDto doctorNoteDto = get(id);
        doctorNoteRepository.deleteById(id);
        return doctorNoteDto;
    }
}
