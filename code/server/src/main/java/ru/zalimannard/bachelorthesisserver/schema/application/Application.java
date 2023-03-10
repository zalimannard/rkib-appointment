package ru.zalimannard.bachelorthesisserver.schema.application;

import jakarta.persistence.*;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.schema.application.status.ApplicationStatus;
import ru.zalimannard.bachelorthesisserver.schema.institution.Institution;
import ru.zalimannard.bachelorthesisserver.schema.patient.Patient;

@Entity
@Table(name = "applications")
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "sending_institution_id")
    private Institution sendingInstitution;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private ApplicationStatus status;

    @Column(name = "doctor_note")
    private String doctorNote;

    @Column(name = "final_diagnosis")
    private String finalDiagnosis;

    @Column(name = "commentary")
    private String commentary;

}
