package ru.zalimannard.rkibappointmentbackend.schema.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
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
