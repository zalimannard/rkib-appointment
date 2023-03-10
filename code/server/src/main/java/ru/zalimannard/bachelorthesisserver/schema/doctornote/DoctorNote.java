package ru.zalimannard.bachelorthesisserver.schema.doctornote;

import jakarta.persistence.*;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.schema.institution.Institution;

@Entity
@Table(name = "doctor_notes")
@Data
public class DoctorNote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false)
    private Institution institution;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

}


