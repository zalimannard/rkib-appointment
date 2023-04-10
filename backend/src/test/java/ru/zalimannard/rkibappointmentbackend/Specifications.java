package ru.zalimannard.rkibappointmentbackend;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Specifications {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

}
