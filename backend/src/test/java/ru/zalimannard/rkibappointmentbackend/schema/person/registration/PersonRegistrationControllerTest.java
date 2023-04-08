package ru.zalimannard.rkibappointmentbackend.schema.person.registration;

import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("Регистрация")
class PersonRegistrationControllerTest {

    private static RestTemplate restTemplate;
    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost";

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl + ":" + port + "/registration";
    }

    @Test
    @DisplayName("Регистрация с корректными параметрами")
    void correctRegistration() {
        Date birthdate = Date.from(Instant.now().minusSeconds(100));
        PersonRegistrationDto request = PersonRegistrationDto.builder()
                .username("vova")
                .password("vova_password")
                .lastName("Путин")
                .firstName("Владимир")
                .patronymic("Владимирович")
                .gender(PersonGender.MALE)
                .phoneNumber("9279279279")
                .birthdate(birthdate)
                .address("Кремль")
                .occupation("Президент")
                .build();
        PersonDto expectedResponse = PersonDto.builder()
                .username("vova")
                .lastName("Путин")
                .firstName("Владимир")
                .patronymic("Владимирович")
                .gender(PersonGender.MALE)
                .phoneNumber("9279279279")
                .birthdate(birthdate)
                .address("Кремль")
                .occupation("Президент")
                .roles(List.of(PersonRole.USER))
                .build();

        PersonDto actualResponse = restTemplate.postForObject(baseUrl, request, PersonDto.class);

        expectedResponse = expectedResponse.toBuilder().id(actualResponse.getId()).build();

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

}