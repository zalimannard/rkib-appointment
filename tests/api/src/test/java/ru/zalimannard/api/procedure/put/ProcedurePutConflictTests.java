package ru.zalimannard.api.procedure.put;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.errors.ErrorResponse;
import ru.zalimannard.api.procedure.ProcedureFactory;
import ru.zalimannard.api.procedure.ProcedureRequest;
import ru.zalimannard.api.procedure.ProcedureResponse;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Услуга")
@Feature("Изменение")
@Story("Негативные тесты. Конфликт")
class ProcedurePutConflictTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление услуги с занятым названием")
    void testPutProcedure_duplicateName_Conflict() {
        ProcedureRequest existedProcedureRequest = ProcedureFactory.createProcedureRequest();
        procedureSteps.post(existedProcedureRequest, adminAuth);

        ProcedureRequest procedureToCreate = ProcedureFactory.createProcedureRequest();
        ProcedureResponse actual = procedureSteps.post(procedureToCreate, adminAuth);

        ErrorResponse actual2 = procedureSteps.put(
                actual.getId(),
                existedProcedureRequest,
                adminAuth,
                specifications.responseSpecificationV1(409),
                ErrorResponse.class
        );
        assertThat(actual2).isNotNull();

        procedureSteps.delete(actual.getId(), adminAuth);
    }

}
