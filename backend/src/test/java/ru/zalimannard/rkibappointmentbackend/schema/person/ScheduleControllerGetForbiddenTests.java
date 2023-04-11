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
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.registration.PersonRegistrationController;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("Человек")
@Feature("Получение человека")
@Story("Неудачное получение")
class ScheduleControllerGetForbiddenTests {

    private final String defaultPassword = "password";
    @Autowired
    private PersonRegistrationController personRegistrationController;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @LocalServerPort
    private int port;
    private String adminAuth;
    private PersonDto defaultPerson;

    @BeforeEach
    void setUp() {
        assertThat(personRegistrationController).isNotNull();
        adminAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(
                System.getenv("ADMIN_USERNAME"), System.getenv("ADMIN_PASSWORD")));
        RestAssured.port = port;

        Date birthdate = Date.from(Instant.now().minusSeconds(100));
        defaultPerson = PersonDto.builder()
                .password(defaultPassword)
                .lastName("Иванов")
                .firstName("Иван")
                .patronymic("Иванович")
                .gender(PersonGender.MALE)
                .phoneNumber("0123456789")
                .birthdate(birthdate)
                .address("Россия, г.Тверь")
                .occupation("Рабочий завода")
                .roles(List.of(PersonRole.PATIENT))
                .build();
        RestAssured.requestSpecification = Specifications.requestSpec();
    }

    @Test
    @DisplayName("Негативный тест. Пациент пытается получить другого пациента")
    void getUserByOtherUser() {
        String usernameA = "patientGetForbidden-1-1";
        String usernameB = "patientGetForbidden-1-2";
        PersonDto personA = defaultPerson.toBuilder()
                .username(usernameA)
                .build();
        PersonDto personB = defaultPerson.toBuilder()
                .username(usernameB)
                .build();
        String personBAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(personB));

        PersonDto createdPersonA = PersonSteps.post(personA, adminAuth);
        PersonSteps.post(personB, adminAuth);

        ExceptionResponse response = PersonSteps.getExpectedForbidden(createdPersonA.getId(), personBAuth);
        assertThat(response).isNotNull();
    }

}