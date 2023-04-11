package ru.zalimannard.rkibappointmentbackend.schema.person.registration;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.zalimannard.rkibappointmentbackend.Specifications;
import ru.zalimannard.rkibappointmentbackend.exception.response.ExceptionResponse;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("Человек")
@Feature("Регистрация пользователя")
@Story("Неудачная регистрация из-за конфликта данных")
class PersonRegistrationControllerPostConflictTests {

    @Autowired
    private PersonRegistrationController personRegistrationController;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        assertThat(personRegistrationController).isNotNull();
        RestAssured.port = port;
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация одинакового пользователя 2 раза")
    void registrationWithoutUsername() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = PersonRegistrationDto.builder()
                .username("userRegCon1")
                .password("password")
                .lastName("Иванов")
                .firstName("Иван")
                .patronymic("Иванович")
                .gender(PersonGender.MALE)
                .phoneNumber("0123456789")
                .birthdate(Date.from(Instant.now().minusSeconds(100)))
                .address("Россия, г.Тверь")
                .build();

        RegistrationSteps.registration(requestPerson, null);
        ExceptionResponse response = RegistrationSteps.registrationExpectedConflict(requestPerson, null);
        assertThat(response).isNotNull();
    }

}