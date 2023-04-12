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
import ru.zalimannard.rkibappointmentbackend.PersonDtoToAuthConverter;
import ru.zalimannard.rkibappointmentbackend.Specifications;
import ru.zalimannard.rkibappointmentbackend.exception.response.ExceptionResponse;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("Человек")
@Feature("Регистрация пользователя")
@Story("Неудачная регистрация из-за ограничения доступа")
class PersonRegistrationControllerPostForbiddenTests {

    @Autowired
    private PersonRegistrationController personRegistrationController;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @LocalServerPort
    private int port;
    private String adminAuth;

    private final PersonRegistrationDto defaultPersonRegistration = RegistrationTestsDefaultDtos.defaultPersonRegistration;
    private final Date birtdate = Date.from(Instant.now().minusSeconds(100));

    @BeforeEach
    void setUp() {
        assertThat(personRegistrationController).isNotNull();
        assertThat(passwordEncoder).isNotNull();
        adminAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(
                System.getenv("ADMIN_USERNAME"), System.getenv("ADMIN_PASSWORD")));
        RestAssured.port = port;
        RestAssured.requestSpecification = Specifications.requestSpec();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация под войденным аккаунтом")
    void registrationWithoutUsername() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setUsername("userRegF1");

        RegistrationSteps.registrationExpectedForbidden(requestPerson, adminAuth);
    }

}