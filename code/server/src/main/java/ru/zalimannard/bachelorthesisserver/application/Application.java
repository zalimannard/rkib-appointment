package ru.zalimannard.bachelorthesisserver.application;

import jakarta.persistence.*;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.application.status.ApplicationStatus;
import ru.zalimannard.bachelorthesisserver.doctornote.DoctorNote;
import ru.zalimannard.bachelorthesisserver.patient.Patient;

@Entity
@Table(name = "applications")
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

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

    @Column(name = "final_diagnosis", nullable = false)
    private String finalDiagnosis;

}
