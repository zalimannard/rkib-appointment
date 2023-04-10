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
import ru.zalimannard.rkibappointmentbackend.Specifications;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonControllerGetTests;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonSteps;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;
import ru.zalimannard.rkibappointmentbackend.security.AuthDataConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("Пользователь")
@Feature("Добавление пользователя")
@Story("Успешная регистрация")
class PersonRegistrationControllerPostCreatedTests {

    @Autowired
    private PersonRegistrationController personRegistrationController;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @LocalServerPort
    private int port;
    private String adminAuth;
    private PersonRegistrationDto defaultPersonRegistration;
    private PersonDto defaultPerson;

    @BeforeEach
    void setUp() {
        assertThat(personRegistrationController).isNotNull();
        assertThat(passwordEncoder).isNotNull();
        adminAuth = passwordEncoder.encode(AuthDataConstructor.construct(
                System.getenv("ADMIN_USERNAME"),
                System.getenv("ADMIN_PASSWORD")));
        RestAssured.port = port;

        Date birthdate = Date.from(Instant.now().minusSeconds(100));
        defaultPersonRegistration = PersonRegistrationDto.builder()
                .password("password")
                .lastName("Иванов")
                .firstName("Иван")
                .patronymic("Иванович")
                .gender(PersonGender.MALE)
                .phoneNumber("0123456789")
                .birthdate(birthdate)
                .address("Россия, г.Тверь")
                .occupation("Рабочий завода")
                .build();
        defaultPerson = PersonDto.builder()
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
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Позитивный тест. Регистрация с полными корректными параметрами")
    void correctRegistrationFull() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegC1")
                .build();
        PersonDto expectedPerson = defaultPerson.toBuilder()
                .username("userRegC1")
                .build();

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация без отчества")
    void correctRegistrationWithoutPatronymic() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegC2")
                .patronymic(null)
                .build();
        PersonDto expectedPerson = defaultPerson.toBuilder()
                .username("userRegC2")
                .patronymic(null)
                .build();

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация без телефона")
    void correctRegistrationWithoutPhone() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegC3")
                .phoneNumber(null)
                .build();
        PersonDto expectedPerson = defaultPerson.toBuilder()
                .username("userRegC3")
                .phoneNumber(null)
                .build();

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация без адреса")
    void correctRegistrationWithoutAddress() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegC4")
                .address(null)
                .build();
        PersonDto expectedPerson = defaultPerson.toBuilder()
                .username("userRegC4")
                .address(null)
                .build();

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация без занятости")
    void correctRegistrationWithoutOccupation() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegC5")
                .occupation(null)
                .build();
        PersonDto expectedPerson = defaultPerson.toBuilder()
                .username("userRegC5")
                .occupation(null)
                .build();

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация с двойной фамилией")
    void correctRegistrationDoubleLastname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegC6")
                .lastName("Чёрный ткач")
                .build();
        PersonDto expectedPerson = defaultPerson.toBuilder()
                .username("userRegC6")
                .lastName("Чёрный ткач")
                .build();

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация с девичьей фамилией")
    void correctRegistrationDoubleLastnameInBrace() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegC7")
                .lastName("Кандеева (Гарипова)")
                .build();
        PersonDto expectedPerson = defaultPerson.toBuilder()
                .username("userRegC7")
                .lastName("Кандеева (Гарипова)")
                .build();

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация с двойным именем")
    void correctRegistrationDoubleFirstname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegC8")
                .firstName("Сань Цзы")
                .build();
        PersonDto expectedPerson = defaultPerson.toBuilder()
                .username("userRegC8")
                .firstName("Сань Цзы")
                .build();

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

    @Test
    @DisplayName("Позитивный тест. Регистрация с двойным отчеством")
    void correctRegistrationDoublePatronymic() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegC9")
                .patronymic("Сань Цзынович")
                .build();
        PersonDto expectedPerson = defaultPerson.toBuilder()
                .username("userRegC9")
                .patronymic("Сань Цзынович")
                .build();

        PersonDto createdPerson = RegistrationSteps.registration(requestPerson, null);

        expectedPerson.setId(createdPerson.getId());
        Assertions.assertEquals(expectedPerson, createdPerson);

        PersonDto existedPerson = PersonSteps.get(createdPerson.getId(), adminAuth);
        Assertions.assertEquals(expectedPerson, existedPerson);
    }

}