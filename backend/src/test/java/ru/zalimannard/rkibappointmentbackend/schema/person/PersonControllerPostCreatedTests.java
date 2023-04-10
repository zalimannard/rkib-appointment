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
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.registration.PersonRegistrationController;
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

    private final String defaultPassword = "password";
    @Autowired
    private PersonRegistrationController personRegistrationController;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @LocalServerPort
    private int port;
    private String adminAuth;
    private PersonDto defaultPersonRequest;
    private PersonDto defaultPersonResponse;

    @BeforeEach
    void setUp() {
        assertThat(personRegistrationController).isNotNull();
        adminAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(
                System.getenv("ADMIN_USERNAME"), System.getenv("ADMIN_PASSWORD")));
        RestAssured.port = port;

        Date birthdate = Date.from(Instant.now().minusSeconds(100));
        defaultPersonRequest = PersonDto.builder()
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
        defaultPersonResponse = defaultPersonRequest.toBuilder()
                .password(null)
                .build();
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Добавление пользователя с полными корректными параметрами админом")
    void correctCreateUserByAdmin() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        String username = "userPostC1";
        PersonDto requestPerson = defaultPersonRequest.toBuilder()
                .username(username)
                .build();
        PersonDto expectedPerson = defaultPersonResponse.toBuilder()
                .username(username)
                .build();

        PersonDto createdPerson = PersonSteps.post(requestPerson, adminAuth);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Добавление доктора с полными корректными параметрами админом")
    void correctCreateDoctorByAdmin() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        String username = "doctorPostC2";
        PersonDto requestPerson = defaultPersonRequest.toBuilder()
                .username(username)
                .roles(List.of(PersonRole.DOCTOR))
                .build();
        PersonDto expectedPerson = defaultPersonResponse.toBuilder()
                .username(username)
                .roles(List.of(PersonRole.DOCTOR))
                .build();

        PersonDto createdPerson = PersonSteps.post(requestPerson, adminAuth);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Добавление регистратора с полными корректными параметрами админом")
    void correctCreateRegistrarByAdmin() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        String username = "registrarPostC3";
        PersonDto requestPerson = defaultPersonRequest.toBuilder()
                .username(username)
                .roles(List.of(PersonRole.REGISTRAR))
                .build();
        PersonDto expectedPerson = defaultPersonResponse.toBuilder()
                .username(username)
                .roles(List.of(PersonRole.REGISTRAR))
                .build();

        PersonDto createdPerson = PersonSteps.post(requestPerson, adminAuth);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Позитивный тест. Добавление админа с полными корректными параметрами админом")
    void correctCreateAdminByAdmin() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        String username = "adminPostC4";
        PersonDto requestPerson = defaultPersonRequest.toBuilder()
                .username(username)
                .roles(List.of(PersonRole.ADMIN))
                .build();
        PersonDto expectedPerson = defaultPersonResponse.toBuilder()
                .username(username)
                .roles(List.of(PersonRole.ADMIN))
                .build();

        PersonDto createdPerson = PersonSteps.post(requestPerson, adminAuth);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Позитивный тест. Добавление пользователя с полными корректными параметрами регистратором")
    void correctRegistrationUserByRegistrar() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonDto registrar = defaultPersonRequest.toBuilder()
                .username("registrarPostC5")
                .password("password")
                .build();
        String registrarAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(registrar));
        PersonSteps.post(registrar, adminAuth);


        String userUsername = "userPostC5";
        PersonDto requestPerson = defaultPersonRequest.toBuilder()
                .username(userUsername)
                .build();
        PersonDto expectedPerson = defaultPersonResponse.toBuilder()
                .username(userUsername)
                .build();


        PersonDto createdPerson = PersonSteps.post(requestPerson, registrarAuth);
        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Позитивный тест. Добавление пользователя с полными корректными параметрами врачом")
    void correctRegistrationUserByDoctor() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonDto doctor = defaultPersonRequest.toBuilder()
                .username("doctorPostC6")
                .password("password")
                .build();
        String doctorAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(doctor));
        PersonSteps.post(doctor, adminAuth);


        String userUsername = "userPostC6";
        PersonDto requestPerson = defaultPersonRequest.toBuilder()
                .username(userUsername)
                .build();
        PersonDto expectedPerson = defaultPersonResponse.toBuilder()
                .username(userUsername)
                .build();


        PersonDto createdPerson = PersonSteps.post(requestPerson, doctorAuth);
        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);
    }

}
