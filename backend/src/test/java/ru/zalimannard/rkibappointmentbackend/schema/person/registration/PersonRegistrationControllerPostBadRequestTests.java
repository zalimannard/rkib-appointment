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
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("Пользователь")
@Feature("Добавление пользователя")
@Story("Негативная регистрация регистрация")
class PersonRegistrationControllerPostBadRequestTests {

    @Autowired
    private PersonRegistrationController personRegistrationController;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @LocalServerPort
    private int port;
    private PersonRegistrationDto defaultPersonRegistration;

    @BeforeEach
    void setUp() {
        assertThat(personRegistrationController).isNotNull();
        assertThat(passwordEncoder).isNotNull();
        RestAssured.port = port;
        defaultPersonRegistration = PersonRegistrationDto.builder()
                .password("password")
                .lastName("Иванов")
                .firstName("Иван")
                .patronymic("Иванович")
                .gender(PersonGender.MALE)
                .phoneNumber("0123456789")
                .birthdate(Date.from(Instant.now().minusSeconds(100)))
                .address("Россия, г.Тверь")
                .build();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация без имени пользователя")
    void correctRegistrationWithoutUsername() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username(null)
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с пустым именем пользователя")
    void correctRegistrationEmptyUsername() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация без пароля")
    void correctRegistrationWithoutPassword() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR3")
                .password(null)
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с пустым паролем")
    void correctRegistrationEmptyPassword() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR4")
                .password("")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация без фамилии")
    void correctRegistrationWithoutLastname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR5")
                .lastName(null)
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пустой фамилией")
    void correctRegistrationEmptyLastname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR6")
                .lastName("")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация без имени")
    void correctRegistrationWithoutFirstname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR7")
                .lastName(null)
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пустым именем")
    void correctRegistrationEmptyFirstname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR8")
                .lastName("")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пустым отчеством")
    void correctRegistrationEmptyPatronymic() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR9")
                .patronymic("")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация без пола")
    void correctRegistrationWithoutGender() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR10")
                .gender(null)
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пустым номером телефона")
    void correctRegistrationEmptyPhoneNumber() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR11")
                .phoneNumber("")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с номером телефона недостаточной длины")
    void correctRegistrationSmallPhoneNumber() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR12")
                .phoneNumber("123123123")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с номером телефона избыточной длины")
    void correctRegistrationLargePhoneNumber() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR13")
                .phoneNumber("12312312312")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с номером телефона содержащим не только цифры")
    void correctRegistrationNotDigitPhoneNumber() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR14")
                .phoneNumber("12312a1232")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Дата рождения в будущем")
    void correctRegistrationFutureBirthdate() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR15")
                .birthdate(Date.from(Instant.now().plusSeconds(10000)))
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пустым адресом")
    void correctRegistrationEmptyAddress() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR16")
                .address("")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пустой занятостью")
    void correctRegistrationEmptyOccupation() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR16")
                .occupation("")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с пробельным именем пользователя")
    void correctRegistrationSpacedUsername() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("   ")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с пробельным паролем")
    void correctRegistrationSpacedPassword() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR19")
                .password("   ")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пробельной фамилией")
    void correctRegistrationSpacedLastname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR20")
                .lastName("   ")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пробельным именем")
    void correctRegistrationSpacedFirstname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR21")
                .lastName("   ")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пробельным отчеством")
    void correctRegistrationSpacedPatronymic() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR22")
                .patronymic("   ")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пробельным номером телефона")
    void correctRegistrationSpacedPhoneNumber() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR23")
                .phoneNumber("   ")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пробельным адресом")
    void correctRegistrationSpacedAddress() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR24")
                .address("   ")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пробельным занятостью")
    void correctRegistrationSpacedOccupation() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR25")
                .occupation("   ")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с именем пользователя на русском")
    void correctRegistrationRussianUsername() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("юзерРегБР26")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с именем пользователя с пробелом")
    void correctRegistrationUsernameWithSpace() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("user RegBR27")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с паролем с русскими символами")
    void correctRegistrationRussianPassword() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR28")
                .password("майПассворд")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с паролем с пробелами")
    void correctRegistrationPasswordWithSpace() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR29")
                .password("pass word")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

}