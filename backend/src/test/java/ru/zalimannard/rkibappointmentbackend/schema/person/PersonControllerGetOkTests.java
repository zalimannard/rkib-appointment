package ru.zalimannard.rkibappointmentbackend.schema.person;

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
@Story("Успешное Получение")
public class PersonControllerGetOkTests {

    private final String defaultPassword = "password";
    @Autowired
    private PersonRegistrationController personRegistrationController;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @LocalServerPort
    private int port;
    private String adminAuth;
    private PersonDto defaultPersonRequest;

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
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Добавление пользователя с полными корректными параметрами админом")
    void correctCreateUserByAdmin() {
        RestAssured.requestSpecification = Specifications.requestSpec();

        String username = "userPostO1";
        PersonDto requestPerson = defaultPersonRequest.toBuilder()
                .username(username)
                .build();

        PersonDto createdPerson = PersonSteps.post(requestPerson, adminAuth);
        assertThat(createdPerson).isNotNull();
    }

}
