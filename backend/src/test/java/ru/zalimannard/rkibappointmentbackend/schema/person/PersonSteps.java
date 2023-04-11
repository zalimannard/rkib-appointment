package ru.zalimannard.rkibappointmentbackend.schema.person;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.zalimannard.rkibappointmentbackend.exception.response.ExceptionResponse;

import static io.restassured.RestAssured.given;

public class PersonSteps {

    private static final String endpoint = "/persons";

    private static ValidatableResponse basePost(PersonDto personDto, String auth) {
        if (auth != null) {
            return given()
                    .headers("Authorization",
                            "Basic " + auth)
                    .body(personDto)
                    .when()
                    .post(endpoint)
                    .then().log().all();
        } else {
            return given()
                    .body(personDto)
                    .when()
                    .post(endpoint)
                    .then().log().all();
        }
    }

    @Step
    public static PersonDto post(PersonDto personDto, String auth) {
        return basePost(personDto, auth)
                .statusCode(201)
                .extract().as(PersonDto.class);
    }

    @Step
    public static ExceptionResponse postExpectedBadRequest(PersonDto personDto, String auth) {
        return basePost(personDto, auth)
                .statusCode(400)
                .extract().as(ExceptionResponse.class);
    }

    @Step
    public static ExceptionResponse postExpectedForbidden(PersonDto personDto, String auth) {
        return basePost(personDto, auth)
                .statusCode(403)
                .extract().as(ExceptionResponse.class);
    }

    @Step
    public static ExceptionResponse postExpectedConflict(PersonDto personDto, String auth) {
        return basePost(personDto, auth)
                .statusCode(409)
                .extract().as(ExceptionResponse.class);
    }


    private static ValidatableResponse baseGet(String id, String auth) {
        if (auth != null) {
            return given()
                    .headers("Authorization",
                            "Basic " + auth)
                    .when()
                    .get(endpoint + "/" + id)
                    .then().log().all();
        } else {
            return given()
                    .when()
                    .when()
                    .get(endpoint + "/" + id)
                    .then().log().all();
        }
    }

    @Step
    public static PersonDto get(String id, String auth) {
        return baseGet(id, auth)
                .statusCode(200)
                .extract().as(PersonDto.class);
    }

    @Step
    public static ExceptionResponse getExpectedForbidden(String id, String auth) {
        return baseGet(id, auth)
                .statusCode(403)
                .extract().as(ExceptionResponse.class);
    }

    @Step
    public static ExceptionResponse getExpectedNotFound(String id, String auth) {
        return baseGet(id, auth)
                .statusCode(404)
                .extract().as(ExceptionResponse.class);
    }

}
