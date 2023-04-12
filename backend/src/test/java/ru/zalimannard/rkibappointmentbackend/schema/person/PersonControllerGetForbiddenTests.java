package ru.zalimannard.rkibappointmentbackend.schema.person;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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
import ru.zalimannard.rkibappointmentbackend.schema.person.registration.PersonRegistrationController;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("Человек")
@Feature("Получение человека")
@Story("Неудачное получение")
class PersonControllerGetForbiddenTests {

    @Autowired
    private PersonRegistrationController personRegistrationController;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @LocalServerPort
    private int port;
    private String adminAuth;
    private final PersonDto defaultPerson = PersonTestsDefaultDtos.defaultPerson;
    private final Date birtdate = Date.from(Instant.now().minusSeconds(100));

    @BeforeEach
    void setUp() {
        assertThat(personRegistrationController).isNotNull();
        adminAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(
                System.getenv("ADMIN_USERNAME"), System.getenv("ADMIN_PASSWORD")));
        RestAssured.port = port;
        RestAssured.requestSpecification = Specifications.requestSpec();
    }

    @Test
    @DisplayName("Негативный тест. Пациент пытается получить другого пациента")
    void getUserByOtherUser() {
        Date birthdate = Date.from(Instant.now().minusSeconds(100));
        PersonDto personA = new PersonDto(defaultPerson);
        personA.setUsername("patientGetForbidden-1-1");
        personA.setBirthdate(birthdate);
        PersonDto personB = new PersonDto(defaultPerson);
        personB.setUsername("patientGetForbidden-1-2");
        personB.setBirthdate(birthdate);

        String personBAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(personB));

        PersonDto createdPersonA = PersonSteps.post(personA, adminAuth);
        PersonSteps.post(personB, adminAuth);

        ExceptionResponse response = PersonSteps.getExpectedForbidden(createdPersonA.getId(), personBAuth);
        assertThat(response).isNotNull();
    }

}