package ru.zalimannard.rkibappointmentbackend.schema.application;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.zalimannard.rkibappointmentbackend.schema.application.status.ApplicationStatus;
import ru.zalimannard.rkibappointmentbackend.schema.institution.Institution;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;

@Entity
@Table(name = "applications")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "Не указан пациент")
    private Person patient;

    @ManyToOne
    @JoinColumn(name = "sending_institution_id")
    private Institution sendingInstitution;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    @NotNull(message = "Не указан статус обращения")
    private ApplicationStatus status;

    @Column(name = "doctor_note")
    @Length(min = 1, message = "Если указано направление, то оно не должно быть пустым")
    private String doctorNote;

    @Column(name = "final_diagnosis")
    @Length(min = 1, message = "Если указан финальный диагноз, то он не должен быть пустым")
    private String finalDiagnosis;

    @Column(name = "commentary")
    @Length(min = 1, message = "Если указан комментарий, то он не должен быть пустым")
    private String commentary;

}