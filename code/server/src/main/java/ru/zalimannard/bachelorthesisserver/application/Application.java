package ru.zalimannard.bachelorthesisserver.application;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.zalimannard.bachelorthesisserver.application.status.ApplicationStatus;
import ru.zalimannard.bachelorthesisserver.doctornote.DoctorNote;
import ru.zalimannard.bachelorthesisserver.patient.Patient;

@Entity
@Table(name = "applications")
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_application_id", nullable = true)
    private Application parentApplication;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_note_id", nullable = false)
    private DoctorNote doctorNote;

    @ManyToOne
    @JoinColumn(name = "application_status_id", nullable = false)
    private ApplicationStatus status;

    @NotNull
    @Column(name = "final_diagnosis", nullable = false)
    private String finalDiagnosis;
}
