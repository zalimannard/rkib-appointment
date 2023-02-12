package ru.zalimannard.bachelorthesisserver.scheduleelments;

import org.springframework.stereotype.Component;
import ru.zalimannard.bachelorthesisserver.doctor.Doctor;
import ru.zalimannard.bachelorthesisserver.doctor.DoctorRepository;
import ru.zalimannard.bachelorthesisserver.exceptions.NotFoundException;
import ru.zalimannard.bachelorthesisserver.scheduleelments.status.ScheduleElementStatus;
import ru.zalimannard.bachelorthesisserver.scheduleelments.status.ScheduleElementStatusRepository;
import ru.zalimannard.bachelorthesisserver.favor.Favor;
import ru.zalimannard.bachelorthesisserver.favor.FavorRepository;

import java.util.Optional;

@Component
public class ScheduleElementMapperImpl implements ScheduleElementMapper {
    private final DoctorRepository doctorRepository;
    private final FavorRepository favorRepository;
    private final ScheduleElementStatusRepository scheduleElementStatusRepository;

    public ScheduleElementMapperImpl(DoctorRepository doctorRepository, FavorRepository favorRepository, ScheduleElementStatusRepository scheduleElementStatusRepository) {
        this.doctorRepository = doctorRepository;
        this.favorRepository = favorRepository;
        this.scheduleElementStatusRepository = scheduleElementStatusRepository;
    }

    @Override
    public ScheduleElement toEntity(ScheduleElementDto dto) {
        return ScheduleElement.builder()
                .id(dto.getId())
                .doctor(obtainDoctor(dto.getDoctorId()))
                .favor(obtainService(dto.getServiceId()))
                .status(obtainScheduleElementStatus(dto.getStatusId()))
                .appointmentTimestamp(dto.getAppointmentTimestamp())
                .build();
    }

    @Override
    public ScheduleElementDto toDto(ScheduleElement entity) {
        return ScheduleElementDto.builder()
                .id(entity.getId())
                .doctorId(obtainDoctorId(entity.getDoctor()))
                .serviceId(obtainServiceId(entity.getFavor()))
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

    private int obtainServiceId(Favor favor) {
        return favor.getId();
    }

    private Favor obtainService(int institutionId) {
        Optional<Favor> serviceOptional = favorRepository.findById(institutionId);
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
