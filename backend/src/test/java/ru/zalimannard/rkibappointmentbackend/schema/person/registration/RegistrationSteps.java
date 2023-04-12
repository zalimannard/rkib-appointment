package ru.zalimannard.rkibappointmentbackend.schema.person.registration;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.zalimannard.rkibappointmentbackend.exception.response.ExceptionResponse;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;
import ru.zalimannard.rkibappointmentbackend.schema.person.gender.PersonGender;

import java.time.Instant;
import java.util.Date;

import static io.restassured.RestAssured.given;

public abstract class RegistrationSteps {

    private static final String endpoint = "/registration";

    private static ValidatableResponse baseRegistration(PersonRegistrationDto personRegistrationDto,
                                                        String auth) {
        if (auth != null) {
            return given()
                    .headers("Authorization",
                            "Basic " + auth)
                    .body(personRegistrationDto)
                    .when()
                    .post(endpoint)
                    .then().log().all();
        } else {
            return given()
                    .body(personRegistrationDto)
                    .when()
                    .post(endpoint)
                    .then().log().all();
        }
    }

    @Step
    public static PersonDto registration(PersonRegistrationDto personRegistrationDto,
                                         String auth) {
        return baseRegistration(personRegistrationDto, auth)
                .statusCode(201)
                .extract().as(PersonDto.class);
    }

    @Step
    public static ExceptionResponse registrationExpectedBadRequest(PersonRegistrationDto personRegistrationDto,
                                                                   String auth) {
        return baseRegistration(personRegistrationDto, auth)
                .statusCode(400)
                .extract().as(ExceptionResponse.class);
    }

    @Step
    public static void registrationExpectedForbidden(PersonRegistrationDto personRegistrationDto,
                                                                  String auth) {
        baseRegistration(personRegistrationDto, auth)
                .statusCode(403);
    }

    @Step
    public static ExceptionResponse registrationExpectedConflict(PersonRegistrationDto personRegistrationDto,
                                                                 String auth) {
        return baseRegistration(personRegistrationDto, auth)
                .statusCode(409)
                .extract().as(ExceptionResponse.class);
    }

}
