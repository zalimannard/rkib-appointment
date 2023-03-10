package ru.zalimannard.bachelorthesisserver.schema.doctor;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper = Mappers.getMapper(DoctorMapper.class);

    @Override
    public DoctorDto get(String id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor", "id", id));
        return doctorMapper.toDto(doctor);
    }

    @Override
    public List<DoctorDto> list(DoctorDto exampleDoctorDto) {
        Doctor exampleDoctor = doctorMapper.toEntity(exampleDoctorDto);
        List<Doctor> doctorList = new ArrayList<>(doctorRepository.findAll(Example.of(exampleDoctor)));
        return doctorMapper.toDtoList(doctorList);
    }

}
