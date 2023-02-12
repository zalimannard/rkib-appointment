package ru.zalimannard.bachelorthesisserver.doctor;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper = Mappers.getMapper(DoctorMapper.class);

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorDto get(int id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor", "id", String.valueOf(id)));
        return doctorMapper.toDto(doctor);
    }

    @Override
    public List<DoctorDto> list() {
        List<Doctor> doctorList = new ArrayList<>();
        doctorRepository.findAll().forEach(doctorList::add);
        return doctorMapper.toDtoList(doctorList);
    }
}
