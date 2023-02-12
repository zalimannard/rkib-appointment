package ru.zalimannard.bachelorthesisserver.doctornote;

import jakarta.persistence.*;
import lombok.Data;
import ru.zalimannard.bachelorthesisserver.institution.Institution;

@Entity
@Table(name = "doctor_notes")
@Data
public class DoctorNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false)
    private Institution institution;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

}


