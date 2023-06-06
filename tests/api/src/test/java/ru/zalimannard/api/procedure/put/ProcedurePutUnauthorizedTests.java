package ru.zalimannard.api.procedure.put;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.procedure.ProcedureFactory;
import ru.zalimannard.api.procedure.ProcedureRequest;

@Epic("Услуга")
@Feature("Изменение")
@Story("Негативные тесты. Запрос без авторизации")
class ProcedurePutUnauthorizedTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Без авторизации")
    void testPutProcedure_AllCorrectData_Unauthorized() {
        ProcedureRequest procedureToCreate = ProcedureFactory.createProcedureRequest();
        procedureSteps.put(
                Faker.instance().internet().uuid(),
                procedureToCreate,
                null,
                specifications.responseSpecificationV1(401),
                null
        );
    }

}
