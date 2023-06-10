package ru.zalimannard.rkibappointmentbackend.schema.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.dto.AppointmentRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.dto.AppointmentResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.AppointmentStatus;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.AppointmentStatusMapper;
import ru.zalimannard.rkibappointmentbackend.schema.institution.Institution;
import ru.zalimannard.rkibappointmentbackend.schema.institution.InstitutionMapper;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.Patient;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.PatientMapper;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {

    private final PatientMapper patientMapper;
    private final InstitutionMapper institutionMapper;
    private final AppointmentStatusMapper appointmentStatusMapper;

    public Appointment toEntity(AppointmentRequestDto appointmentRequestDto,
                                Patient patient,
                                Institution institution,
                                AppointmentStatus status) {
        return Appointment.builder()
                .patient(patient)
                .institution(institution)
                .status(status)
                .doctorNote(appointmentRequestDto.getDoctorNote())
                .diagnosis(appointmentRequestDto.getDiagnosis())
                .commentary(appointmentRequestDto.getCommentary())
                .build();
    }

    public AppointmentResponseDto toDto(Appointment appointment) {
        return AppointmentResponseDto.builder()
                .id(appointment.getId())
                .patient(patientMapper.toDto(appointment.getPatient()))
                .institution(institutionMapper.toDto(appointment.getInstitution()))
                .status(appointmentStatusMapper.toDto(appointment.getStatus()))
                .doctorNote(appointment.getDoctorNote())
                .diagnosis(appointment.getDiagnosis())
                .commentary(appointment.getCommentary())
                .build();
    }

    public List<AppointmentResponseDto> toDtoList(List<Appointment> appointments) {
        return appointments.stream().map(this::toDto).toList();
    }

}
