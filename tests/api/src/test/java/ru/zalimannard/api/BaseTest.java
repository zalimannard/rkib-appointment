package ru.zalimannard.api;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.zalimannard.api.institution.InstitutionSteps;
import ru.zalimannard.api.person.PersonSteps;
import ru.zalimannard.api.procedure.ProcedureSteps;

import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseTest {

    @Autowired
    protected Specifications specifications;
    @Autowired
    protected PersonSteps personSteps;
    @Autowired
    protected InstitutionSteps institutionSteps;
    @Autowired
    protected ProcedureSteps procedureSteps;
    protected String adminAuth;

    @LocalServerPort
    private int port;
    @Value("${application.init.admin.username}")
    private String adminUsername;
    @Value("${application.init.admin.password}")
    private String adminPassword;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.requestSpecification = specifications.requestSpecApiV1();

        adminAuth = Base64.getEncoder().encodeToString((adminUsername + ":" + adminPassword).getBytes());
    }

}
