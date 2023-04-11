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
@Story("Неудачная регистрация из-за неправильных данных")
class PersonRegistrationControllerPostBadRequestTests {

    @Autowired
    private PersonRegistrationController personRegistrationController;

    @LocalServerPort
    private int port;
    private PersonRegistrationDto defaultPersonRegistration;

    @BeforeEach
    void setUp() {
        assertThat(personRegistrationController).isNotNull();
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
    void registrationWithoutUsername() {
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
    void registrationEmptyUsername() {
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
    void registrationWithoutPassword() {
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
    void registrationEmptyPassword() {
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
    void registrationWithoutLastname() {
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
    void registrationEmptyLastname() {
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
    void registrationWithoutFirstname() {
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
    void registrationEmptyFirstname() {
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
    void registrationEmptyPatronymic() {
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
    void registrationWithoutGender() {
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
    void registrationEmptyPhoneNumber() {
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
    void registrationSmallPhoneNumber() {
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
    void registrationLargePhoneNumber() {
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
    void registrationNotDigitPhoneNumber() {
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
    void registrationFutureBirthdate() {
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
    void registrationEmptyAddress() {
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
    void registrationEmptyOccupation() {
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
    void registrationSpacedUsername() {
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
    void registrationSpacedPassword() {
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
    void registrationSpacedLastname() {
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
    void registrationSpacedFirstname() {
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
    void registrationSpacedPatronymic() {
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
    void registrationSpacedPhoneNumber() {
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
    void registrationSpacedAddress() {
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
    void registrationSpacedOccupation() {
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
    void registrationRussianUsername() {
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
    void registrationUsernameWithSpace() {
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
    void registrationRussianPassword() {
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
    void registrationPasswordWithSpace() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = defaultPersonRegistration.toBuilder()
                .username("userRegBR29")
                .password("pass word")
                .build();

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

}