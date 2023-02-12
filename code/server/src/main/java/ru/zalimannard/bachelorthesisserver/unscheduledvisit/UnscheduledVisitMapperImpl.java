package ru.zalimannard.bachelorthesisserver.unscheduledvisit;

import org.springframework.stereotype.Component;
import ru.zalimannard.bachelorthesisserver.application.Application;
import ru.zalimannard.bachelorthesisserver.application.ApplicationRepository;
import ru.zalimannard.bachelorthesisserver.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.doctor.DoctorRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.favor.Favor;
import ru.zalimannard.bachelorthesisserver.favor.FavorRepository;

import java.util.Optional;

@Component
public class UnscheduledVisitMapperImpl implements UnscheduledVisitMapper {
    private final DoctorRepository doctorRepository;
    private final FavorRepository favorRepository;
    private final ApplicationRepository applicationRepository;

    public UnscheduledVisitMapperImpl(DoctorRepository doctorRepository, FavorRepository favorRepository, ApplicationRepository applicationRepository) {
        this.doctorRepository = doctorRepository;
        this.favorRepository = favorRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public UnscheduledVisit toEntity(UnscheduledVisitDto dto) {
        return UnscheduledVisit.builder()
                .id(dto.getId())
                .doctor(obtainDoctor(dto.getDoctorId()))
                .favor(obtainService(dto.getServiceId()))
                .application(obtainApplication(dto.getApplicationId()))
                .appointmentTimestamp(dto.getAppointmentTimestamp())
                .comment(dto.getComment())
                .build();
    }

    @Override
    public UnscheduledVisitDto toDto(UnscheduledVisit entity) {
        return UnscheduledVisitDto.builder()
                .id(entity.getId())
                .doctorId(obtainDoctorId(entity.getDoctor()))
                .serviceId(obtainServiceId(entity.getFavor()))
                .applicationId(obtainApplicationId(entity.getApplication()))
                .appointmentTimestamp(entity.getAppointmentTimestamp())
                .comment(entity.getComment())
                .build();
    }

    private int obtainDoctorId(Doctor doctor) {
        return doctor.getId();
    }

    private Doctor obtainDoctor(int doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (doctorOptional.isPresent()) {
            return doctorOptional.get();
        } else {
            throw new NotFoundException("Вложенного во внеплановое учреждение доктора не существует");
        }
    }

    private int obtainServiceId(Favor favor) {
        return favor.getId();
    }

    private Favor obtainService(int serviceId) {
        Optional<Favor> serviceOptional = favorRepository.findById(serviceId);
        if (serviceOptional.isPresent()) {
            return serviceOptional.get();
        } else {
            throw new NotFoundException("Вложенной во внеплановое учреждение услуги не существует");
        }
    }

    private int obtainApplicationId(Application application) {
        return application.getId();
    }

    private Application obtainApplication(int applicationId) {
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isPresent()) {
            return applicationOptional.get();
        } else {
            throw new NotFoundException("Вложенного во внеплановое учреждение обращения не существует");
        }
    }
}
