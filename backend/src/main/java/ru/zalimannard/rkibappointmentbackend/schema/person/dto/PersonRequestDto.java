package ru.zalimannard.rkibappointmentbackend.schema.person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder(toBuilder = true)
public class PersonRequestDto {

    @JsonProperty("username")
    @NotBlank(message = "Не указано имя пользователя")
    String username;

    @JsonProperty("password")
    @NotBlank(message = "Не указан пароль")
    @Pattern(regexp = "[a-zA-Z0-9!@#%?_]*", message = "Недопустимые символы в пароле")
    String password;

    @JsonProperty("email")
    @NotBlank(message = "Не указан Email")
    @Email(message = "Некорректный Email")
    String email;

    @JsonProperty("lastName")
    @NotBlank(message = "Не указана фамилия")
    String lastName;

    @JsonProperty("firstName")
    @NotBlank(message = "Не указано имя")
    String firstName;

    @JsonProperty("patronymic")
    @Length(min = 1)
    String patronymic;

    public PersonRequestDto(String username, String password, String email, String lastName, String firstName, String patronymic) {
        this.username = username != null ? username.trim() : null;
        this.password = password;
        this.email = email != null ? email.trim() : null;
        this.lastName = lastName != null ? lastName.trim() : null;
        this.firstName = firstName != null ? firstName.trim() : null;
        this.patronymic = patronymic != null ? patronymic.trim() : null;
    }

}
