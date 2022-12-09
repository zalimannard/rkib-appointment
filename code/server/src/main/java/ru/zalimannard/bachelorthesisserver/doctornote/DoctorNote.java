package ru.zalimannard.bachelorthesisserver.doctornote;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
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
    Integer id;

    @NotNull
    @Column(name = "diagnosis")
    String diagnosis;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false)
    Institution institution;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DoctorNote that = (DoctorNote) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


