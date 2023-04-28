package ru.zalimannard.rkibappointmentbackend.schema.person.patient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import ru.zalimannard.rkibappointmentbackend.validator.Phone;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class PatientRequestDto {

    @JsonProperty("personId")
    @NotBlank(message = "Не указан человек")
    String personId;

    @JsonProperty("phoneNumber")
    @NotNull(message = "Не указан номер телефона")
    @Phone(message = "Недопустимый номер телефона")
    String phoneNumber;

    @JsonProperty("birthdate")
    @Past(message = "Дата рождения должна быть в прошлом")
    Date birthdate;

    @JsonProperty("address")
    @Length(min = 1)
    String address;

    @JsonProperty("occupation")
    @Length(min = 1)
    String occupation;

    public PatientRequestDto(String personId, String phoneNumber, Date birthdate, String address, String occupation) {
        this.personId = personId;
        this.phoneNumber = phoneNumber != null ? phoneNumber.trim() : null;
        this.birthdate = birthdate;
        this.address = address != null ? address.trim() : null;
        this.occupation = occupation != null ? occupation.trim() : null;
    }

}
