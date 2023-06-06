package ru.zalimannard.api.procedure.post;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.procedure.ProcedureFactory;
import ru.zalimannard.api.procedure.ProcedureRequest;

@Epic("Услуга")
@Feature("Добавление")
@Story("Негативные тесты. Запрос без авторизации")
class ProcedurePutUnauthorizedTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Без авторизации")
    void testCreateProcedure_AllCorrectData_Unauthorized() {
        ProcedureRequest procedureToCreate = ProcedureFactory.createProcedureRequest();
        procedureSteps.post(
                procedureToCreate,
                null,
                specifications.responseSpecificationV1(401),
                null
        );
    }

}
