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

    private final PersonRegistrationDto defaultPersonRegistration = RegistrationTestsDefaultDtos.defaultPersonRegistration;
    private final Date birtdate = Date.from(Instant.now().minusSeconds(100));

    @BeforeEach
    void setUp() {
        assertThat(personRegistrationController).isNotNull();
        RestAssured.port = port;
        RestAssured.requestSpecification = Specifications.requestSpec();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация без имени пользователя")
    void registrationWithoutUsername() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername(null);

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

//    @Test
//    @Severity(SeverityLevel.CRITICAL)
//    @DisplayName("Негативный тест. Регистрация с пустым именем пользователя")
//    void registrationEmptyUsername() {
//        RestAssured.requestSpecification = Specifications.requestSpec();
//
//        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
//        requestPerson.setBirthdate(birtdate);
//        requestPerson.setUsername("");
//
//        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
//        assertThat(response).isNotNull();
//    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация без пароля")
    void registrationWithoutPassword() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR3");
        requestPerson.setPassword(null);

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с пустым паролем")
    void registrationEmptyPassword() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR4");
        requestPerson.setPassword("");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация без фамилии")
    void registrationWithoutLastname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR5");
        requestPerson.setLastName(null);

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пустой фамилией")
    void registrationEmptyLastname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR6");
        requestPerson.setLastName("");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация без имени")
    void registrationWithoutFirstname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR7");
        requestPerson.setFirstName(null);

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пустым именем")
    void registrationEmptyFirstname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR8");
        requestPerson.setFirstName("");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пустым отчеством")
    void registrationEmptyPatronymic() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR9");
        requestPerson.setPatronymic("");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация без пола")
    void registrationWithoutGender() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR10");
        requestPerson.setGender(null);

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пустым номером телефона")
    void registrationEmptyPhoneNumber() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR11");
        requestPerson.setPhoneNumber("");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с номером телефона недостаточной длины")
    void registrationSmallPhoneNumber() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR12");
        requestPerson.setPhoneNumber("123123123");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с номером телефона избыточной длины")
    void registrationLargePhoneNumber() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR13");
        requestPerson.setPhoneNumber("12312312312");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с номером телефона содержащим не только цифры")
    void registrationNotDigitPhoneNumber() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR14");
        requestPerson.setPhoneNumber("12312a1232");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Дата рождения в будущем")
    void registrationFutureBirthdate() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR15");
        requestPerson.setBirthdate(Date.from(Instant.now().plusSeconds(10000)));

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пустым адресом")
    void registrationEmptyAddress() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR16");
        requestPerson.setAddress("");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пустой занятостью")
    void registrationEmptyOccupation() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR17");
        requestPerson.setOccupation("");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с пробельным именем пользователя")
    void registrationSpacedUsername() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("   ");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с пробельным паролем")
    void registrationSpacedPassword() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR19");
        requestPerson.setPatronymic("   ");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пробельной фамилией")
    void registrationSpacedLastname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR20");
        requestPerson.setLastName("   ");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пробельным именем")
    void registrationSpacedFirstname() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR21");
        requestPerson.setFirstName("   ");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пробельным отчеством")
    void registrationSpacedPatronymic() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR22");
        requestPerson.setPatronymic("   ");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пробельным номером телефона")
    void registrationSpacedPhoneNumber() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR23");
        requestPerson.setPhoneNumber("   ");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пробельным адресом")
    void registrationSpacedAddress() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR24");
        requestPerson.setAddress("   ");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Негативный тест. Регистрация с пробельным занятостью")
    void registrationSpacedOccupation() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR25");
        requestPerson.setOccupation("   ");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с именем пользователя на русском")
    void registrationRussianUsername() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("юзерРегБР26");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с именем пользователя с пробелом")
    void registrationUsernameWithSpace() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("user RegBR27");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с паролем с русскими символами")
    void registrationRussianPassword() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR28");
        requestPerson.setPassword("майПассворд");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Негативный тест. Регистрация с паролем с пробелами")
    void registrationPasswordWithSpace() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        PersonRegistrationDto requestPerson = new PersonRegistrationDto(defaultPersonRegistration);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userRegBR29");
        requestPerson.setPassword("pass word");

        ExceptionResponse response = RegistrationSteps.registrationExpectedBadRequest(requestPerson, null);
        assertThat(response).isNotNull();
    }

}