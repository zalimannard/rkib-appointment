package ru.zalimannard.rkibappointmentbackend.schema.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.zalimannard.rkibappointmentbackend.exception.ConflictException;
import ru.zalimannard.rkibappointmentbackend.exception.NotFoundException;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.dto.AppointmentRequestDto;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.dto.AppointmentResponseDto;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.AppointmentStatus;
import ru.zalimannard.rkibappointmentbackend.schema.appointment.status.AppointmentStatusService;
import ru.zalimannard.rkibappointmentbackend.schema.institution.Institution;
import ru.zalimannard.rkibappointmentbackend.schema.institution.InstitutionService;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.Patient;
import ru.zalimannard.rkibappointmentbackend.schema.person.patient.PatientService;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentMapper mapper;
    private final AppointmentRepository repository;

    private final PatientService patientService;
    private final InstitutionService institutionService;
    private final AppointmentStatusService appointmentStatusService;

    @Override
    public AppointmentResponseDto create(AppointmentRequestDto appointmentDto) {
        Patient patient = patientService.readEntity(appointmentDto.getPatientId());
        Institution institution = institutionService.readEntity(appointmentDto.getInstitutionId());
        AppointmentStatus status = appointmentStatusService.readEntity(appointmentDto.getStatusId());

        Appointment appointmentToCreate = mapper.toEntity(appointmentDto, patient, institution, status);
        Appointment createdAppointment = createEntity(appointmentToCreate);
        return mapper.toDto(createdAppointment);
    }

    @Override
    public Appointment createEntity(Appointment appointment) {
        try {
            return repository.save(appointment);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("aps-01", "Конфликт при добавлении Appointment в базу данных", e.getMessage());
        }
    }

    @Override
    public AppointmentResponseDto read(String id) {
        Appointment appointment = readEntity(id);
        return mapper.toDto(appointment);
    }

    @Override
    public Appointment readEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("aps-02", "Не найден Appointment с id=" + id, null));
    }

    @Override
    public AppointmentResponseDto update(String id, AppointmentRequestDto appointmentDto) {
        Patient patient = patientService.readEntity(appointmentDto.getPatientId());
        Institution institution = institutionService.readEntity(appointmentDto.getInstitutionId());
        AppointmentStatus status = appointmentStatusService.readEntity(appointmentDto.getStatusId());

        Appointment appointmentToUpdate = mapper.toEntity(appointmentDto, patient, institution, status);
        appointmentToUpdate.setId(id);
        Appointment updatedAppointment = updateEntity(appointmentToUpdate);
        return mapper.toDto(updatedAppointment);
    }

    @Override
    public Appointment updateEntity(Appointment appointment) {
        try {
            return repository.save(appointment);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("aps-03", "Конфликт при обновлении Appointment в базе данных", e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Appointment appointment = readEntity(id);
            repository.delete(appointment);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("aps-04", "Конфликт при удалении Appointment из базы данных", e.getMessage());
        }
    }

}
