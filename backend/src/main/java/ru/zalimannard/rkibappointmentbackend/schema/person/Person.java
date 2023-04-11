package ru.zalimannard.rkibappointmentbackend.schema.person;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "persons")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Person {

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
    @Pattern(regexp = "[a-zA-Zа-яА-ЯЁё()-]+([ ]?[a-zA-Zа-яА-ЯЁё()-]+)*", message = "Недопустимые символы в фамилии")
    private String lastName;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Имя не должно быть пустым")
    @Pattern(regexp = "[a-zA-Zа-яА-ЯЁё()-]+([ ]?[a-zA-Zа-яА-ЯЁё()-]+)*", message = "Недопустимые символы в имени")
    private String firstName;

    @Column(name = "patronymic")
    @Length(min = 1, message = "Если указано отчество, то оно должен быть ненулевой длины")
    @Pattern(regexp = "[a-zA-Zа-яА-ЯЁё()-]+([ ]?[a-zA-Zа-яА-ЯЁё()-]+)*", message = "Недопустимые символы в отчестве")
    private String patronymic;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\d{10}", message = "Неверный формат номера")
    private String phoneNumber;

    @Column(name = "birthdate", nullable = false)
    @Past(message = "Дата рождения должна быть в прошлом")
    private Date birthdate;

    @Column(name = "address")
    @Length(min = 1, message = "Если указан адрес, то он должен быть ненулевой длины")
    @Pattern(regexp = "[a-zA-Z0-9а-яА-ЯЁё/().,-]+([ ]?[a-zA-Z0-9а-яА-ЯЁё/().,-]*)*", message = "Недопустимые символы в адресе")
    private String address;

    @Column(name = "occupation")
    @Length(min = 1, message = "Если указана занятость, то она должна быть ненулевой длины")
    @Pattern(regexp = "[a-zA-Zа-яА-ЯЁё().,-]+([ ]?[a-zA-Zа-яА-ЯЁё().,-]+)*", message = "Недопустимые символы в занятости")
    private String occupation;

    @Column(name = "gender", nullable = false)
    @NotNull(message = "Не указан пол")
    private PersonGender gender;

    @Column(name = "roles", nullable = false)
    @NotNull(message = "Роли не переданы")
    @NotEmpty(message = "Не указана ни одна роль")
    private List<PersonRole> roles;
}
