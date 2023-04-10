package ru.zalimannard.rkibappointmentbackend.schema.person;

import io.qameta.allure.Step;
import ru.zalimannard.rkibappointmentbackend.schema.person.registration.PersonRegistrationDto;

import static io.restassured.RestAssured.given;

public class PersonSteps {

    private static final String endpoint = "/persons";

    @Step
    public static PersonDto get(String id, String auth) {
        if (auth != null) {
            return given()
                    .headers("Authorization",
                            "Basic " + auth)
                    .when()
                    .get(endpoint + "/" + id)
                    .then().log().all()
                    .statusCode(200)
                    .extract().as(PersonDto.class);
        } else {
            return given()
                    .when()
                    .when()
                    .get(endpoint + "/" + id)
                    .then().log().all()
                    .statusCode(200)
                    .extract().as(PersonDto.class);
        }
    }

}
