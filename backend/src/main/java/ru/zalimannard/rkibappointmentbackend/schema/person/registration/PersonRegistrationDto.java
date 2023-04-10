package ru.zalimannard.rkibappointmentbackend.schema.person.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PersonRegistrationDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
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

}