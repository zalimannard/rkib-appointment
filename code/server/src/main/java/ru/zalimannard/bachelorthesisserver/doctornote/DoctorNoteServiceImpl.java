package ru.zalimannard.bachelorthesisserver.doctornote;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorNoteServiceImpl implements DoctorNoteService {
    private final DoctorNoteRepository doctorNoteRepository;
    private final DoctorNoteMapper doctorNoteMapper = Mappers.getMapper(DoctorNoteMapper.class);

    public DoctorNoteServiceImpl(DoctorNoteRepository doctorNoteRepository) {
        this.doctorNoteRepository = doctorNoteRepository;
    }

    @Override
    public DoctorNoteDto get(int id) {
        DoctorNote doctorNote = doctorNoteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("DoctorNote", "id", String.valueOf(id)));
        return doctorNoteMapper.toDto(doctorNote);
    }

    @Override
    public List<DoctorNoteDto> list() {
        List<DoctorNote> doctorNoteList = new ArrayList<>();
        doctorNoteRepository.findAll().forEach(doctorNoteList::add);
        return doctorNoteMapper.toDtoList(doctorNoteList);
    }

    @Override
    public DoctorNoteDto create(DoctorNoteDto doctorNoteDto) {
        DoctorNote doctorNoteRequest = doctorNoteMapper.toEntity(doctorNoteDto);
        DoctorNote doctorNoteResponse = doctorNoteRepository.save(doctorNoteRequest);
        return doctorNoteMapper.toDto(doctorNoteResponse);
    }

    @Override
    public DoctorNoteDto update(DoctorNoteDto doctorNoteDto) {
        DoctorNote doctorNoteRequest = doctorNoteMapper.toEntity(doctorNoteDto);
        if (doctorNoteRepository.existsById(doctorNoteRequest.getId())) {
            DoctorNote doctorNoteResponse = doctorNoteRepository.save(doctorNoteRequest);
            return doctorNoteMapper.toDto(doctorNoteResponse);
        } else {
            throw new NotFoundException("DoctorNote", "id", String.valueOf(doctorNoteRequest.getId()));
        }
    }

    @Override
    public DoctorNoteDto delete(int id) {
        DoctorNote doctorNote = doctorNoteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("DoctorNote", "id", String.valueOf(id)));
        doctorNoteRepository.deleteById(id);
        return doctorNoteMapper.toDto(doctorNote);
    }
}
