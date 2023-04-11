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
@Feature("Получение человека")
@Story("Успешное получение")
public class ScheduleControllerGetOkTests {

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
        RestAssured.requestSpecification = Specifications.requestSpec();
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Получение пациента админом")
    void getUserByAdmin() {
        String patientUsername = "userGetOk-1";
        PersonDto requestPerson = defaultPersonRequest.toBuilder()
                .username(patientUsername)
                .build();

        PersonDto createdPatient = PersonSteps.post(requestPerson, adminAuth);
        assertThat(createdPatient).isNotNull();

        PersonDto gotPatient = PersonSteps.get(createdPatient.getId(), adminAuth);
        Assertions.assertEquals(createdPatient, gotPatient);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Получение пациента регистратором")
    void getUserByRegistrar() {
        String registrarUsername = "registrarGetOk-2";
        String patientUsername = "patientGetOk-2";

        PersonDto registrar = defaultPersonRequest.toBuilder()
                .username(registrarUsername)
                .roles(List.of(PersonRole.REGISTRAR))
                .build();
        String registrarAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(registrar));
        PersonSteps.post(registrar, adminAuth);

        PersonDto patient = defaultPersonRequest.toBuilder()
                .username(patientUsername)
                .build();
        PersonDto createdPatient = PersonSteps.post(patient, adminAuth);

        PersonDto gotPatient = PersonSteps.get(createdPatient.getId(), registrarAuth);
        Assertions.assertEquals(createdPatient, gotPatient);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Получение пациента доктором")
    void getUserByDoctor() {
        String doctorUsername = "doctorGetOk-3";
        String patientUsername = "patientGetOk-3";

        PersonDto doctor = defaultPersonRequest.toBuilder()
                .username(doctorUsername)
                .roles(List.of(PersonRole.DOCTOR))
                .build();
        String doctorAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(doctor));
        PersonSteps.post(doctor, adminAuth);

        PersonDto patient = defaultPersonRequest.toBuilder()
                .username(patientUsername)
                .build();
        PersonDto createdPatient = PersonSteps.post(patient, adminAuth);

        PersonDto gotPatient = PersonSteps.get(createdPatient.getId(), doctorAuth);
        Assertions.assertEquals(createdPatient, gotPatient);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Получение регистратора доктором")
    void getRegistrarByDoctor() {
        String doctorUsername = "doctorGetOk-4";
        String registrarUsername = "registrarGetOk-4";

        PersonDto doctor = defaultPersonRequest.toBuilder()
                .username(doctorUsername)
                .roles(List.of(PersonRole.DOCTOR))
                .build();
        String doctorAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(doctor));
        PersonSteps.post(doctor, adminAuth);

        PersonDto registrar = defaultPersonRequest.toBuilder()
                .username(registrarUsername)
                .roles(List.of(PersonRole.REGISTRAR))
                .build();
        PersonDto createdRegistrar = PersonSteps.post(registrar, adminAuth);

        PersonDto gotRegistrar = PersonSteps.get(createdRegistrar.getId(), doctorAuth);
        Assertions.assertEquals(createdRegistrar, gotRegistrar);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Получение доктора регистратором")
    void getDoctorByRegistrar() {
        String registrarUsername = "registrarGetOk-5";
        String doctorUsername = "doctorGetOk-5";

        PersonDto registrar = defaultPersonRequest.toBuilder()
                .username(doctorUsername)
                .roles(List.of(PersonRole.REGISTRAR))
                .build();
        String registrarAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(registrar));
        PersonSteps.post(registrar, adminAuth);

        PersonDto doctor = defaultPersonRequest.toBuilder()
                .username(registrarUsername)
                .roles(List.of(PersonRole.DOCTOR))
                .build();
        PersonDto createdDoctor = PersonSteps.post(doctor, adminAuth);

        PersonDto gotDoctor = PersonSteps.get(createdDoctor.getId(), registrarAuth);
        Assertions.assertEquals(createdDoctor, gotDoctor);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Пациент получает человека с ролями пациент и доктор")
    void getDoctorAndPatientByPatient() {
        String patientUsername = "patientGetOk-6";
        String doctorPatientUsername = "doctorPatientGetOk-6";

        PersonDto patient = defaultPersonRequest.toBuilder()
                .username(patientUsername)
                .roles(List.of(PersonRole.PATIENT))
                .build();
        String patientAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(patient));
        PersonSteps.post(patient, adminAuth);

        PersonDto doctorPatient = defaultPersonRequest.toBuilder()
                .username(doctorPatientUsername)
                .roles(List.of(PersonRole.DOCTOR, PersonRole.PATIENT))
                .build();
        PersonDto createdDoctorPatient = PersonSteps.post(doctorPatient, adminAuth);

        PersonDto gotDoctorPatient = PersonSteps.get(createdDoctorPatient.getId(), patientAuth);
        Assertions.assertEquals(createdDoctorPatient, gotDoctorPatient);
    }

}
