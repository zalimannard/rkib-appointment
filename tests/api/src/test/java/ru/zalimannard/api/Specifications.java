package ru.zalimannard.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Specifications {

    @Value("${application.serverUrl}")
    private String serverUrl;
    @Value("${application.baseApi}")
    private String baseApi;
    @Value("${application.apiV1}")
    private String apiV1;

    public RequestSpecification requestSpecApiV1() {
        return new RequestSpecBuilder()
                .setBaseUri(serverUrl + baseApi + apiV1)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }

    public ResponseSpecification responseSpecificationV1(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

}
