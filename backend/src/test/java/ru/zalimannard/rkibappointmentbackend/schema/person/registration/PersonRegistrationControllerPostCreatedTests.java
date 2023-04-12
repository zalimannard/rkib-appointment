package ru.zalimannard.rkibappointmentbackend.schema.person.registration;

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
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonSteps;

import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("Человек")
@Feature("Регистрация пользователя")
@Story("Успешная регистрация")
class PersonRegistrationControllerPostCreatedTests {

    @Autowired
    private PersonRegistrationController personRegistrationController;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @LocalServerPort
    private int port;
    private String adminAuth;

    private final PersonRegistrationDto defaultPersonRegistration = RegistrationTestsDefaultDtos.defaultPersonRegistration;
    private final Date birtdate = Date.from(Instant.now().minusSeconds(100));
    private final PersonDto defaultPerson = RegistrationTestsDefaultDtos.defaultCreatedPerson;

    @BeforeEach
    void setUp() {
        assertThat(personRegistrationController).isNotNull();
        adminAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(
                System.getenv("ADMIN_USERNAME"), System.getenv("ADMIN_PASSWORD")));
        RestAssured.port = port;
        RestAssured.requestSpecification = Specifications.requestSpec();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Позитивный тест. Регистрация с полными корректными параметрами")
    void correctRegistrationFull() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegC1");
        PersonDto expectedPerson = new PersonDto(defaultPerson);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername("userRegC1");

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);
        createdPerson.setPassword(null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация без отчества")
    void correctRegistrationWithoutPatronymic() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegC2");
        requestPerson.setPatronymic(null);
        PersonDto expectedPerson = new PersonDto(defaultPerson);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername("userRegC2");
        expectedPerson.setPatronymic(null);

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);
        createdPerson.setPassword(null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация без телефона")
    void correctRegistrationWithoutPhone() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegC3");
        requestPerson.setPhoneNumber(null);
        PersonDto expectedPerson = new PersonDto(defaultPerson);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername("userRegC3");
        expectedPerson.setPhoneNumber(null);

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);
        createdPerson.setPassword(null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация без адреса")
    void correctRegistrationWithoutAddress() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegC4");
        requestPerson.setAddress(null);
        PersonDto expectedPerson = new PersonDto(defaultPerson);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername("userRegC4");
        expectedPerson.setAddress(null);

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);
        createdPerson.setPassword(null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация без занятости")
    void correctRegistrationWithoutOccupation() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegC5");
        requestPerson.setOccupation(null);
        PersonDto expectedPerson = new PersonDto(defaultPerson);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername("userRegC5");
        expectedPerson.setOccupation(null);

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);
        createdPerson.setPassword(null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация с двойной фамилией")
    void correctRegistrationDoubleLastname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegC6");
        requestPerson.setLastName("Чёрный ткач");
        PersonDto expectedPerson = new PersonDto(defaultPerson);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername("userRegC6");
        expectedPerson.setLastName("Чёрный ткач");

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);
        createdPerson.setPassword(null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация с девичьей фамилией")
    void correctRegistrationDoubleLastnameInBrace() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegC7");
        requestPerson.setLastName("Кандеева (Гарипова)");
        PersonDto expectedPerson = new PersonDto(defaultPerson);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername("userRegC7");
        expectedPerson.setLastName("Кандеева (Гарипова)");

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);
        createdPerson.setPassword(null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация с двойным именем")
    void correctRegistrationDoubleFirstname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegC8");
        requestPerson.setFirstName("Сань Цзы");
        PersonDto expectedPerson = new PersonDto(defaultPerson);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername("userRegC8");
        expectedPerson.setFirstName("Сань Цзы");

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);
        createdPerson.setPassword(null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация с двойным отчеством")
    void correctRegistrationDoublePatronymic() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegC9");
        requestPerson.setPatronymic("Сань Цзынович");
        PersonDto expectedPerson = new PersonDto(defaultPerson);
        expectedPerson.setBirthdate(birtdate);
        expectedPerson.setUsername("userRegC9");
        expectedPerson.setPatronymic("Сань Цзынович");

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);
        createdPerson.setPassword(null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        existedPerson.setPassword(null);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

}
