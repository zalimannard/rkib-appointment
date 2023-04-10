package ru.zalimannard.rkibappointmentbackend.schema.person.registration;

import io.qameta.allure.Step;
import ru.zalimannard.rkibappointmentbackend.exception.response.ExceptionResponse;
import ru.zalimannard.rkibappointmentbackend.schema.person.PersonDto;

import static io.restassured.RestAssured.given;

public abstract class RegistrationSteps {

    private static final String endpoint = "/registration";

    @Step
    public static PersonDto registration(PersonRegistrationDto personRegistrationDto,
                                         String auth) {
        if (auth != null) {
            return given()
                    .headers("Authorization",
                            "Basic " + auth)
                    .body(personRegistrationDto)
                    .when()
                    .post(endpoint)
                    .then().log().all()
                    .statusCode(201)
                    .extract().as(PersonDto.class);
        } else {
            return given()
                    .body(personRegistrationDto)
                    .when()
                    .post(endpoint)
                    .then().log().all()
                    .statusCode(201)
                    .extract().as(PersonDto.class);
        }
    }

    @Step
    public static ExceptionResponse registrationExpectedBadRequest(PersonRegistrationDto personRegistrationDto,
                                                                   String auth) {
        if (auth != null) {
            return given()
                    .headers("Authorization",
                            "Basic " + auth)
                    .body(personRegistrationDto)
                    .when()
                    .post(endpoint)
                    .then().log().all()
                    .statusCode(400)
                    .extract().as(ExceptionResponse.class);
        } else {
            return given()
                    .body(personRegistrationDto)
                    .when()
                    .post(endpoint)
                    .then().log().all()
                    .statusCode(400)
                    .extract().as(ExceptionResponse.class);
        }
    }

    @Step
    public static ExceptionResponse registrationExpectedForbidden(PersonRegistrationDto personRegistrationDto,
                                                                  String auth) {
        if (auth != null) {
            return given()
                    .headers("Authorization",
                            "Basic " + auth)
                    .body(personRegistrationDto)
                    .when()
                    .post(endpoint)
                    .then().log().all()
                    .statusCode(403)
                    .extract().as(ExceptionResponse.class);
        } else {
            return given()
                    .body(personRegistrationDto)
                    .when()
                    .post(endpoint)
                    .then().log().all()
                    .statusCode(403)
                    .extract().as(ExceptionResponse.class);
        }
    }

}
