package ru.zalimannard.rkibappointmentbackend.schema.person.staffmember;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import ru.zalimannard.rkibappointmentbackend.schema.person.Person;
import ru.zalimannard.rkibappointmentbackend.schema.person.staffmember.role.StaffMemberRole;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "staff")
@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class StaffMember {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private Person person;

    @Column(name = "roles", nullable = false)
    private Set<StaffMemberRole> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StaffMember that = (StaffMember) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
