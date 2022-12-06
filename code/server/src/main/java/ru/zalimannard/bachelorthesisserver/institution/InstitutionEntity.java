package ru.zalimannard.bachelorthesisserver.institution;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "institutions")
public class InstitutionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    public InstitutionDto toDto(boolean expand) {
        InstitutionDto dto = new InstitutionDto();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }
}
