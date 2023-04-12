package ru.zalimannard.rkibappointmentbackend.schema.person;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "persons")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Person {

    public Person(Person other) {
        id = other.getId();
        username = other.getUsername();
        password = other.getPassword();
        lastName = other.getLastName();
        firstName = other.getFirstName();
        patronymic = other.getPatronymic();
        phoneNumber = other.getPhoneNumber();
        birthdate = other.getBirthdate();
        address = other.getAddress();
        occupation = other.getOccupation();
        gender = other.getGender();
        roles = other.getRoles().stream().toList();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "Имя пользователя должно быть у каждого")
    @Pattern(regexp = "[a-zA-Z0-9.-]+", message = "Недопустимые символы в имени пользователя")
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Фамилия не должна быть пустой")
    @Pattern(regexp = "[a-zA-Zа-яА-ЯЁё()-]+( ?[a-zA-Zа-яА-ЯЁё()-]+)*", message = "Недопустимые символы в фамилии")
    private String lastName;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Имя не должно быть пустым")
    @Pattern(regexp = "[a-zA-Zа-яА-ЯЁё()-]+( ?[a-zA-Zа-яА-ЯЁё()-]+)*", message = "Недопустимые символы в имени")
    private String firstName;

    @Column(name = "patronymic")
    @Length(min = 1, message = "Если указано отчество, то оно должен быть ненулевой длины")
    @Pattern(regexp = "[a-zA-Zа-яА-ЯЁё()-]+( ?[a-zA-Zа-яА-ЯЁё()-]+)*", message = "Недопустимые символы в отчестве")
    private String patronymic;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\d{10}", message = "Неверный формат номера")
    private String phoneNumber;

    @Column(name = "birthdate", nullable = false)
    @Past(message = "Дата рождения должна быть в прошлом")
    private Date birthdate;

    @Column(name = "address")
    @Length(min = 1, message = "Если указан адрес, то он должен быть ненулевой длины")
    @Pattern(regexp = "[a-zA-Z0-9а-яА-ЯЁё/().,-]+( ?[a-zA-Z0-9а-яА-ЯЁё/().,-]*)*", message = "Недопустимые символы в адресе")
    private String address;

    @Column(name = "occupation")
    @Length(min = 1, message = "Если указана занятость, то она должна быть ненулевой длины")
    @Pattern(regexp = "[a-zA-Zа-яА-ЯЁё().,-]+( ?[a-zA-Zа-яА-ЯЁё().,-]+)*", message = "Недопустимые символы в занятости")
    private String occupation;

    @Column(name = "gender", nullable = false)
    @NotNull(message = "Не указан пол")
    private PersonGender gender;

    @Column(name = "roles", nullable = false)
    @NotNull(message = "Роли не переданы")
    @NotEmpty(message = "Не указана ни одна роль")
    private List<PersonRole> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return getId() != null && Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
