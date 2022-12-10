package ru.zalimannard.bachelorthesisserver.doctor;

import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorMapper doctorMapper, DoctorRepository doctorRepository) {
        this.doctorMapper = doctorMapper;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorDto read(int id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            return doctorMapper.toDto(doctor);
        } else {
            throw new NotFoundException("Доктор с id=" + id + " не найден");
        }
    }

    @Override
    public List<DoctorDto> list() {
        Iterable<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDto> doctorDtos = new ArrayList<>();
        doctors.forEach(doctor -> doctorDtos.add(doctorMapper.toDto(doctor)));
        return doctorDtos;
    }
}
