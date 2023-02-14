package ru.zalimannard.bachelorthesisserver.doctornote;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.institution.InstitutionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorNoteServiceImpl implements DoctorNoteService {
    private final DoctorNoteRepository doctorNoteRepository;
    private final InstitutionRepository institutionRepository;
    private final DoctorNoteMapper doctorNoteMapper = Mappers.getMapper(DoctorNoteMapper.class);

    @Override
    public DoctorNoteDto get(String id) {
        DoctorNote doctorNote = doctorNoteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("DoctorNote", "id", id));
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
        DoctorNote doctorNoteRequest = doctorNoteMapper.toEntity(doctorNoteDto, institutionRepository);
        DoctorNote doctorNoteResponse = doctorNoteRepository.save(doctorNoteRequest);
        return doctorNoteMapper.toDto(doctorNoteResponse);
    }

    @Override
    public DoctorNoteDto update(DoctorNoteDto doctorNoteDto) {
        DoctorNote doctorNoteRequest = doctorNoteMapper.toEntity(doctorNoteDto, institutionRepository);
        if (doctorNoteRepository.existsById(doctorNoteRequest.getId())) {
            DoctorNote doctorNoteResponse = doctorNoteRepository.save(doctorNoteRequest);
            return doctorNoteMapper.toDto(doctorNoteResponse);
        } else {
            throw new NotFoundException("DoctorNote", "id", String.valueOf(doctorNoteRequest.getId()));
        }
    }

    @Override
    public DoctorNoteDto delete(String id) {
        DoctorNote doctorNote = doctorNoteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("DoctorNote", "id", id));
        doctorNoteRepository.deleteById(id);
        return doctorNoteMapper.toDto(doctorNote);
    }
}
