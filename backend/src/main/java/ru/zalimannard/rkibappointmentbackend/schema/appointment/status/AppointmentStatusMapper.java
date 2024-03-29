package ru.zalimannard.rkibappointmentbackend.schema.appointment.status;

import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.dto.AppointmentStatusRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.dto.AppointmentStatusResponseDto;

import java.util.List;

@Component
public class AppointmentStatusMapper {

    public AppointmentStatus toEntity(AppointmentStatusRequestDto appointmentStatusRequestDto) {
        return AppointmentStatus.builder()
                .type(appointmentStatusRequestDto.getType())
                .name(appointmentStatusRequestDto.getName())
                .build();
    }

    public AppointmentStatusResponseDto toDto(AppointmentStatus appointmentStatus) {
        return AppointmentStatusResponseDto.builder()
                .id(appointmentStatus.getId())
                .type(appointmentStatus.getType())
                .name(appointmentStatus.getName())
                .build();
    }

    public List<AppointmentStatusResponseDto> toDtoList(List<AppointmentStatus> appointmentStatuses) {
        return appointmentStatuses.stream().map(this::toDto).toList();
    }

}
