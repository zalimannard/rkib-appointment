package ru.zalimannard.rkibappointmentbackend.schema.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    public PersonDto(PersonDto other) {
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

    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "Пароль должен быть у каждого")
    @Pattern(regexp = "[a-zA-Z0-9!@#%?_]+", message = "Недопустимые символы в пароле")
    private String password;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty("gender")
    private PersonGender gender;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("birthdate")
    private Date birthdate;

    @JsonProperty("address")
    private String address;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("roles")
    private List<PersonRole> roles;
}
