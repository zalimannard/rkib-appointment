package ru.zalimannard.rkibappointmentbackend.schema.person.registration;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.zalimannard.rkibappointmentbackend.Specifications;
import ru.zalimannard.rkibappointmentbackend.exception.response.ExceptionResponse;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.security.AuthDataConstructor;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("Пользователь")
@Feature("Добавление пользователя")
@Story("Неудачная регистрация из-за ограничения доступа")
class PersonRegistrationControllerPostForbiddenTests {

    @Autowired
    private PersonRegistrationController personRegistrationController;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @LocalServerPort
    private int port;
    private String adminAuth;

    @BeforeEach
    void setUp() {
        assertThat(personRegistrationController).isNotNull();
        assertThat(passwordEncoder).isNotNull();
        adminAuth = passwordEncoder.encode(AuthDataConstructor.construct(
                System.getenv("ADMIN_USERNAME"),
                System.getenv("ADMIN_PASSWORD")));
        RestAssured.port = port;
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация под войденным аккаунтом")
    void correctRegistrationWithoutUsername() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = PersonRegistrationDto.builder()
                .username("userRegF1")
                .password("password")
                .lastName("Иванов")
                .firstName("Иван")
                .patronymic("Иванович")
                .gender(PersonGender.MALE)
                .phoneNumber("0123456789")
                .birthdate(Date.from(Instant.now().minusSeconds(100)))
                .address("Россия, г.Тверь")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedForbidden(requestPerson, adminAuth);
        assertThat(response).isNotNull();
    }

}