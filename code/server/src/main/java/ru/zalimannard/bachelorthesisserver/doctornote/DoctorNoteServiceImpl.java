package ru.zalimannard.bachelorthesisserver.doctornote;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

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
    public DoctorNoteDto read(int id) {
        Optional<DoctorNote> doctorNoteOptional = doctorNoteRepository.findById(id);
        if (doctorNoteOptional.isPresent()) {
            DoctorNote doctorNote = doctorNoteOptional.get();
            return doctorNoteMapper.toDto(doctorNote);
        } else {
            throw new NotFoundException("Направление с id=" + id + " не найдено.");
        }
    }

    @Override
    public List<DoctorNoteDto> list() {
        Iterable<DoctorNote> doctorNotes = doctorNoteRepository.findAll();
        List<DoctorNoteDto> doctorNoteDtos = new ArrayList<>();
        doctorNotes.forEach(doctorNote -> doctorNoteDtos.add(doctorNoteMapper.toDto(doctorNote)));
        return doctorNoteDtos;
    }

    @Override
    public DoctorNoteDto create(DoctorNoteDto doctorNoteDto) {
        DoctorNote doctorNote = doctorNoteMapper.toEntity(doctorNoteDto);
        DoctorNote addedDoctorNote = doctorNoteRepository.save(doctorNote);
        return doctorNoteMapper.toDto(addedDoctorNote);
    }

    @Override
    public DoctorNoteDto update(DoctorNoteDto doctorNoteDto) {
        if (doctorNoteRepository.existsById(doctorNoteDto.getId())) {
            DoctorNote doctorNote = doctorNoteMapper.toEntity(doctorNoteDto);
            doctorNoteRepository.save(doctorNote);
            return read(doctorNote.getId());
        } else {
            throw new NotFoundException("Направление с id=" + doctorNoteDto.getId() + " не найдено. Ничего не изменено.");
        }
    }

    @Override
    public DoctorNoteDto delete(int id) {
        DoctorNoteDto doctorNoteDto = read(id);
        doctorNoteRepository.deleteById(id);
        return doctorNoteDto;
    }
}
