package ru.zalimannard.bachelorthesisserver.scheduleelments;

import org.springframework.stereotype.Component;
import ru.zalimannard.bachelorthesisserver.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.doctor.DoctorRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.scheduleelments.status.ScheduleElementStatus;
import ru.zalimannard.bachelorthesisserver.scheduleelments.status.ScheduleElementStatusRepository;
import ru.zalimannard.bachelorthesisserver.service.Service;
import ru.zalimannard.bachelorthesisserver.service.ServiceRepository;

import java.util.Optional;

@Component
public class ScheduleElementMapperImpl implements ScheduleElementMapper {
    private final DoctorRepository doctorRepository;
    private final ServiceRepository serviceRepository;
    private final ScheduleElementStatusRepository scheduleElementStatusRepository;

    public ScheduleElementMapperImpl(DoctorRepository doctorRepository, ServiceRepository serviceRepository, ScheduleElementStatusRepository scheduleElementStatusRepository) {
        this.doctorRepository = doctorRepository;
        this.serviceRepository = serviceRepository;
        this.scheduleElementStatusRepository = scheduleElementStatusRepository;
    }

    @Override
    public ScheduleElement toEntity(ScheduleElementDto dto) {
        return ScheduleElement.builder()
                .id(dto.getId())
                .doctor(obtainDoctor(dto.getDoctorId()))
                .service(obtainService(dto.getServiceId()))
                .status(obtainScheduleElementStatus(dto.getStatusId()))
                .appointmentTimestamp(dto.getAppointmentTimestamp())
                .build();
    }

    @Override
    public ScheduleElementDto toDto(ScheduleElement entity) {
        return ScheduleElementDto.builder()
                .id(entity.getId())
                .doctorId(obtainDoctorId(entity.getDoctor()))
                .serviceId(obtainServiceId(entity.getService()))
                .statusId(obtainScheduleElementStatusId(entity.getStatus()))
                .appointmentTimestamp(entity.getAppointmentTimestamp())
                .build();
    }

    private int obtainDoctorId(Doctor doctor) {
        return doctor.getId();
    }

    private Doctor obtainDoctor(int institutionId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(institutionId);
        if (doctorOptional.isPresent()) {
            return doctorOptional.get();
        } else {
            throw new NotFoundException("Вложенного в элемент доктора  не существует");
        }
    }

    private int obtainServiceId(Service service) {
        return service.getId();
    }

    private Service obtainService(int institutionId) {
        Optional<Service> serviceOptional = serviceRepository.findById(institutionId);
        if (serviceOptional.isPresent()) {
            return serviceOptional.get();
        } else {
            throw new NotFoundException("Вложенной в элемент расписания услуги не существует");
        }
    }

    private int obtainScheduleElementStatusId(ScheduleElementStatus scheduleElementStatus) {
        return scheduleElementStatus.getId();
    }

    private ScheduleElementStatus obtainScheduleElementStatus(int institutionId) {
        Optional<ScheduleElementStatus> scheduleElementStatusOptional = scheduleElementStatusRepository.findById(institutionId);
        if (scheduleElementStatusOptional.isPresent()) {
            return scheduleElementStatusOptional.get();
        } else {
            throw new NotFoundException("Вложенного в элемент расписания стасуса не существует");
        }
    }
}
