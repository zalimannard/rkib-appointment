package ru.zalimannard.rkibappointmentbackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.zalimannard.rkibappointmentbackend.schema.person.registration.PersonRegistrationController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RkibAppointmentBackendApplicationTests {

    @Autowired
    private PersonRegistrationController personRegistrationController;

    @Test
    void contextLoads() {
        assertThat(personRegistrationController).isNotNull();
    }

}
