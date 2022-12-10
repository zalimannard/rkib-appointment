package ru.zalimannard.bachelorthesisserver.doctornote;

import jakarta.persistence.*;
import lombok.*;
import ru.zalimannard.bachelorthesisserver.institution.Institution;

import java.util.Objects;

@Entity
@Table(name = "doctor_notes")
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class DoctorNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false)
    private Institution institution;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorNote that = (DoctorNote) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


