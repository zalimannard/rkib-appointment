package ru.zalimannard.api.procedure.post;

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
@Feature("Добавление")
@Story("Негативные тесты. Конфликт")
class ProcedurePostConflictTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление учреждения с занятым названием")
    void testCreateProcedure_duplicateName_Conflict() {
        ProcedureRequest procedureToCreate = ProcedureFactory.createProcedureRequest();
        ProcedureResponse actual = procedureSteps.post(
                procedureToCreate,
                adminAuth,
                specifications.responseSpecificationV1(201),
                ProcedureResponse.class
        );

        ErrorResponse actual2 = procedureSteps.post(
                procedureToCreate,
                adminAuth,
                specifications.responseSpecificationV1(409),
                ErrorResponse.class
        );
        assertThat(actual2).isNotNull();

        procedureSteps.delete(actual.getId(), adminAuth);
    }

}
