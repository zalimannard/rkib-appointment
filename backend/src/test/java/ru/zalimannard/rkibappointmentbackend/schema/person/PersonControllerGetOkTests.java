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
import ru.zalimannard.rkibappointmentbackend.schema.person.role.PersonRole;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Epic("Человек")
@Feature("Получение человека")
@Story("Успешное получение")
public class PersonControllerGetOkTests {

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
    @DisplayName("Позитивный тест. Получение пациента админом")
    void getUserByAdmin() {
        PersonDto requestPerson = new PersonDto(defaultPersonRequest);
        requestPerson.setBirthdate(birtdate);
        requestPerson.setUsername("userGetOk-1");

        PersonDto createdPatient = PersonSteps.post(requestPerson, adminAuth);
        assertThat(createdPatient).isNotNull();

        PersonDto gotPatient = PersonSteps.get(createdPatient.getId(), adminAuth);
        gotPatient.setPassword(null);
        Assertions.assertEquals(createdPatient, gotPatient);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Получение пациента регистратором")
    void getUserByRegistrar() {
        PersonDto registrar = new PersonDto(defaultPersonRequest);
        registrar.setBirthdate(birtdate);
        registrar.setUsername("registrarGetOk-2");
        registrar.setRoles(List.of(PersonRole.REGISTRAR));
        String registrarAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(registrar));

        PersonDto patient = new PersonDto(defaultPersonRequest);
        patient.setBirthdate(birtdate);
        patient.setUsername("patientGetOk-2");
        PersonDto createdPatient = PersonSteps.post(patient, adminAuth);

        PersonDto gotPatient = PersonSteps.get(createdPatient.getId(), registrarAuth);
        gotPatient.setPassword(null);
        Assertions.assertEquals(createdPatient, gotPatient);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Получение пациента доктором")
    void getUserByDoctor() {
        PersonDto doctor = new PersonDto(defaultPersonRequest);
        doctor.setBirthdate(birtdate);
        doctor.setUsername("doctorGetOk-3");
        doctor.setRoles(List.of(PersonRole.DOCTOR));
        String doctorAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(doctor));

        PersonDto patient = new PersonDto(defaultPersonRequest);
        patient.setBirthdate(birtdate);
        patient.setUsername("patientGetOk-3");
        PersonDto createdPatient = PersonSteps.post(patient, adminAuth);

        PersonDto gotPatient = PersonSteps.get(createdPatient.getId(), doctorAuth);
        gotPatient.setPassword(null);
        Assertions.assertEquals(createdPatient, gotPatient);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Получение регистратора доктором")
    void getRegistrarByDoctor() {
        PersonDto doctor = new PersonDto(defaultPersonRequest);
        doctor.setBirthdate(birtdate);
        doctor.setUsername("doctorGetOk-4");
        doctor.setRoles(List.of(PersonRole.DOCTOR));
        String doctorAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(doctor));

        PersonDto registrar = new PersonDto(defaultPersonRequest);
        registrar.setBirthdate(birtdate);
        registrar.setUsername("registrarGetOk-4");
        registrar.setRoles(List.of(PersonRole.REGISTRAR));
        PersonDto createdRegistrar = PersonSteps.post(registrar, adminAuth);

        PersonDto gotRegistrar = PersonSteps.get(createdRegistrar.getId(), doctorAuth);
        gotRegistrar.setPassword(null);
        Assertions.assertEquals(createdRegistrar, gotRegistrar);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Получение доктора регистратором")
    void getDoctorByRegistrar() {
        PersonDto registrar = new PersonDto(defaultPersonRequest);
        registrar.setBirthdate(birtdate);
        registrar.setUsername("registrarGetOk-5");
        registrar.setRoles(List.of(PersonRole.REGISTRAR));
        String registrarAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(registrar));
        PersonSteps.post(registrar, adminAuth);

        PersonDto doctor = new PersonDto(defaultPersonRequest);
        doctor.setBirthdate(birtdate);
        doctor.setUsername("doctorGetOk-5");
        doctor.setRoles(List.of(PersonRole.DOCTOR));
        PersonDto createdDoctor = PersonSteps.post(doctor, adminAuth);

        PersonDto gotDoctor = PersonSteps.get(createdDoctor.getId(), registrarAuth);
        gotDoctor.setPassword(null);
        Assertions.assertEquals(createdDoctor, gotDoctor);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Позитивный тест. Пациент получает человека с ролями пациент и доктор")
    void getDoctorAndPatientByPatient() {
        PersonDto patient = new PersonDto(defaultPersonRequest);
        patient.setBirthdate(birtdate);
        patient.setUsername("patientGetOk-6");
        patient.setRoles(List.of(PersonRole.REGISTRAR));
        String patientAuth = passwordEncoder.encode(PersonDtoToAuthConverter.convert(patient));
        PersonSteps.post(patient, adminAuth);

        PersonDto doctorPatient = new PersonDto(defaultPersonRequest);
        doctorPatient.setBirthdate(birtdate);
        doctorPatient.setUsername("doctorPatientGetOk-6");
        doctorPatient.setRoles(List.of(PersonRole.DOCTOR));
        PersonDto createdDoctorPatient = PersonSteps.post(doctorPatient, adminAuth);

        PersonDto gotDoctorPatient = PersonSteps.get(createdDoctorPatient.getId(), patientAuth);
        gotDoctorPatient.setPassword(null);
        Assertions.assertEquals(createdDoctorPatient, gotDoctorPatient);
    }

}
