package ru.zalimannard.rkibappointmentbackend;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RkibAppointmentBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RkibAppointmentBackendApplication.class, args);
    }

}
