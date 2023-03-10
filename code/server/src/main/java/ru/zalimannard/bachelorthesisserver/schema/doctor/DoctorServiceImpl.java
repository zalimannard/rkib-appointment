package ru.zalimannard.bachelorthesisserver.schema.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundExceptionHttp;
import ru.zalimannard.bachelorthesisserver.utils.Utils;
import ru.zalimannard.bachelorthesisserver.utils.mapper.MappingType;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    public DoctorDto read(String id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionHttp("${application.entityNames.doctor}", id));
        return doctorMapper.toDto(doctor);
    }

    @Override
    public List<DoctorDto> search(DoctorDto filterDoctorDto, int pageNo, int pageSize, String[] sort) {
        Doctor filterDoctor = doctorMapper.toEntity(filterDoctorDto, MappingType.FORCE);
        List<Sort.Order> orders = Utils.ordersByStringArray(sort);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(orders));

        List<Doctor> doctorList = doctorRepository.search(filterDoctor.getLastName(),
                filterDoctor.getFirstName(), filterDoctor.getPatronymic(), pageable);
        return doctorMapper.toDtoList(doctorList);
    }

}
