package ru.zalimannard.rkibappointmentbackend.schema.person;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.zalimannard.rkibappointmentbackend.PersonDtoToAuthConverter;
import ru.zalimannard.rkibappointmentbackend.Specifications;
import ru.zalimannard.rkibappointmentbackend.schema.person.registration.PersonRegistrationController;
import ru.zalimannard.rkibappointmentbackend.schema.person.registration.RegistrationTestsDefaultDtos;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("Человек")
@Feature("Добавление человека")
@Story("Успешное добавление")
class PersonControllerPostCreatedTests {

    @Autowired
    private PersonRegistrationController personRegistrationController;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @LocalServerPort
    private int port;
    private String adminAuth;
    private final PersonDto defaultPersonRequest = PersonTestsDefaultDtos.defaultPerson;
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
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Добавление пользователя с полными корректными параметрами админом")
    void correctCreateUserByAdmin() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        String username = "userPostC1";
        PersonDto requestPerson = new PersonDto(defaultPersonRequest);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername(username);
        PersonDto expectedPerson = new PersonDto(defaultPersonRequest);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername(username);
        expectedPerson.setPassword(null);

        PersonDto createdPerson = PersonSteps.post(requestPerson, adminAuth);
        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Добавление доктора с полными корректными параметрами админом")
    void correctCreateDoctorByAdmin() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        String username = "doctorPostC2";
        PersonDto requestPerson = new PersonDto(defaultPersonRequest);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername(username);
        requestPerson.setRoles(List.of(PersonRole.DOCTOR));
        PersonDto expectedPerson = new PersonDto(defaultPersonRequest);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername(username);
        expectedPerson.setPassword(null);
        expectedPerson.setRoles(List.of(PersonRole.DOCTOR));

        PersonDto createdPerson = PersonSteps.post(requestPerson, adminAuth);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Добавление регистратора с полными корректными параметрами админом")
    void correctCreateRegistrarByAdmin() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        String username = "registrarPostC3";
        PersonDto requestPerson = new PersonDto(defaultPersonRequest);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername(username);
        requestPerson.setRoles(List.of(PersonRole.REGISTRAR));
        PersonDto expectedPerson = new PersonDto(defaultPersonRequest);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername(username);
        expectedPerson.setPassword(null);
        expectedPerson.setRoles(List.of(PersonRole.REGISTRAR));

        PersonDto createdPerson = PersonSteps.post(requestPerson, adminAuth);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Позитивный тест. Добавление админа с полными корректными параметрами админом")
    void correctCreateAdminByAdmin() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        String username = "adminPostC4";
        PersonDto requestPerson = new PersonDto(defaultPersonRequest);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername(username);
        requestPerson.setRoles(List.of(PersonRole.ADMIN));
        PersonDto expectedPerson = new PersonDto(defaultPersonRequest);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername(username);
        expectedPerson.setPassword(null);
        expectedPerson.setRoles(List.of(PersonRole.ADMIN));

        PersonDto createdPerson = PersonSteps.post(requestPerson, adminAuth);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Позитивный тест. Добавление пользователя с полными корректными параметрами регистратором")
    void correctRegistrationUserByRegistrar() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonDto registrar = new PersonDto(defaultPersonRequest);
        registrar.setBirthdate(birtdate);
        registrar.setUsername("registrarPostC5");
        registrar.setRoles(List.of(PersonRole.REGISTRAR));
        String registrarAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(registrar));
        PersonSteps.post(registrar, adminAuth);

        String username = "userPostC5";
        PersonDto requestPerson = new PersonDto(defaultPersonRequest);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername(username);
        PersonDto expectedPerson = new PersonDto(defaultPersonRequest);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername(username);
        expectedPerson.setPassword(null);

        PersonDto createdPerson = PersonSteps.post(requestPerson, registrarAuth);
        expectedPerson.setId(createdPerson.getId());
        expectedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, createdPerson);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Позитивный тест. Добавление пользователя с полными корректными параметрами врачом")
    void correctRegistrationUserByDoctor() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonDto doctor = new PersonDto(defaultPersonRequest);
        doctor.setBirthdate(birtdate);
        doctor.setUsername("doctorPostC6");
        doctor.setRoles(List.of(PersonRole.REGISTRAR));
        String doctorAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(doctor));
        PersonSteps.post(doctor, adminAuth);

        String username = "userPostC6";
        PersonDto requestPerson = new PersonDto(defaultPersonRequest);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername(username);
        PersonDto expectedPerson = new PersonDto(defaultPersonRequest);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername(username);
        expectedPerson.setPassword(null);

        PersonDto createdPerson = PersonSteps.post(requestPerson, doctorAuth);
        expectedPerson.setId(createdPerson.getId());
        expectedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, createdPerson);
    }

}
