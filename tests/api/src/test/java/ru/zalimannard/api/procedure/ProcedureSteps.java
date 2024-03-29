package ru.zalimannard.api.procedure;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Component
public class ProcedureSteps {

    @Value("${application.endpoints.procedures}")
    private String endpoint;

    public ProcedureResponse get(String id, String auth) {
        ResponseSpecification responseSpec = RestAssured.expect()
                .statusCode(200);

        return get(id, auth, responseSpec, ProcedureResponse.class);
    }

    public ProcedureResponse post(ProcedureRequest body, String auth) {
        ResponseSpecification responseSpec = RestAssured.expect()
                .statusCode(201);

        return post(body, auth, responseSpec, ProcedureResponse.class);
    }

    public ProcedureResponse put(String id, ProcedureRequest body, String auth) {
        ResponseSpecification responseSpec = RestAssured.expect()
                .statusCode(200);

        return put(id, body, auth, responseSpec, ProcedureResponse.class);
    }

    public void delete(String id,
                       String auth) {
        ResponseSpecification responseSpec = RestAssured.expect()
                .statusCode(204);

        delete(id, auth, responseSpec, ResponseBody.class);
    }

    @Step("Получение учреждения")
    public <T> T get(String id,
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
                .get(endpoint + "/" + id);
        response.then().spec(responseSpec);
        try {
            return response.as(responseClass);
        } catch (Exception e) {
            return null;
        }
    }

    @Step("Создание учреждения")
    public <T> T post(ProcedureRequest body,
                      String auth, ResponseSpecification responseSpec,
                      Class<T> responseClass) {
        RequestSpecification requestSpec = given();
        if (auth != null) {
            requestSpec.header(
                    "Authorization",
                    "Basic " + auth);
        }
        Response response = requestSpec
                .body(body)
                .when()
                .post(endpoint);
        response.then().spec(responseSpec);
        try {
            return response.as(responseClass);
        } catch (Exception e) {
            return null;
        }
    }

    @Step("Изменение учреждения")
    public <T> T put(String id, ProcedureRequest body,
                     String auth, ResponseSpecification responseSpec,
                     Class<T> responseClass) {
        RequestSpecification requestSpec = given();
        if (auth != null) {
            requestSpec.header(
                    "Authorization",
                    "Basic " + auth);
        }
        Response response = requestSpec
                .body(body)
                .when()
                .put(endpoint + "/" + id);
        response.then().spec(responseSpec);
        try {
            return response.as(responseClass);
        } catch (Exception e) {
            return null;
        }
    }

    @Step("Удаление учреждения")
    public <T> T delete(String id,
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
        try {
            return response.as(responseClass);
        } catch (Exception e) {
            return null;
        }
    }

}
