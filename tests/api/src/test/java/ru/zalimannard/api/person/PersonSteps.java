package ru.zalimannard.api.person;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static io.restassured.RestAssured.given;

@Component
public class PersonSteps {

    @Value("${application.endpoints.people}")
    private String endpoint;

    public PersonResponse get(String id, String auth) {
        ResponseSpecification responseSpec = RestAssured.expect()
                .statusCode(200);

        return get(id, auth, responseSpec, PersonResponse.class);
    }

    public PersonResponse post(PersonRequest body, String auth) {
        ResponseSpecification responseSpec = RestAssured.expect()
                .statusCode(201);

        return post(body, auth, responseSpec, PersonResponse.class);
    }

    public PersonResponse put(String id, PersonRequest body, String auth) {
        ResponseSpecification responseSpec = RestAssured.expect()
                .statusCode(200);

        return put(id, body, auth, responseSpec, PersonResponse.class);
    }

    public void delete(String id,
                       String auth) {
        ResponseSpecification responseSpec = RestAssured.expect()
                .statusCode(204);

        delete(id, auth, responseSpec, ResponseBody.class);
    }

    @Step("Получение человека")
    public <T> T get(String id,
                     String auth, ResponseSpecification responseSpec,
                     Class<T> responseClass) {
        RequestSpecification requestSpec = given();
        if (auth != null) {
            requestSpec.header(
                    "Authorization",
                    "Basic " + auth);
        }
        return requestSpec
                .when()
                .get(endpoint + "/" + id)
                .then()
                .spec(responseSpec)
                .extract().as(responseClass);
    }

    @Step("Создание человека")
    public <T> T post(PersonRequest body,
                      String auth, ResponseSpecification responseSpec,
                      Class<T> responseClass) {
        RequestSpecification requestSpec = given();
        if (auth != null) {
            requestSpec.header(
                    "Authorization",
                    "Basic " + auth);
        }
        return requestSpec
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .spec(responseSpec)
                .extract().as(responseClass);
    }

    @Step("Изменение человека")
    public <T> T put(String id, PersonRequest body,
                     String auth, ResponseSpecification responseSpec,
                     Class<T> responseClass) {
        RequestSpecification requestSpec = given();
        if (auth != null) {
            requestSpec.header(
                    "Authorization",
                    "Basic " + auth);
        }
        return requestSpec
                .body(body)
                .when()
                .put(endpoint + "/" + id)
                .then()
                .spec(responseSpec)
                .extract().as(responseClass);
    }

    @Step("Удаление человека")
    public <T> Optional<T> delete(String id,
                                  String auth, ResponseSpecification responseSpec,
                                  Class<T> responseClass) {
        RequestSpecification requestSpec = given();
        if (auth != null) {
            requestSpec.header(
                    "Authorization",
                    "Basic " + auth);
        }
        Response response = requestSpec
                .when()
                .delete(endpoint + "/" + id);
        response.then().spec(responseSpec);

        if (response.getStatusCode() == 204) {
            return Optional.empty();
        } else {
            return Optional.of(response.as(responseClass));
        }
    }

}
