package ru.zalimannard.api.procedure.put;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.procedure.ProcedureFactory;
import ru.zalimannard.api.procedure.ProcedureRequest;
import ru.zalimannard.api.procedure.ProcedureResponse;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Услуга")
@Feature("Изменение")
@Story("Позитивные тесты")
class ProcedurePutOkTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешное изменение от ADMIN")
    void testPutProcedure_AllCorrectDataByAdmin_Ok() {
        ProcedureRequest existedProcedureRequest = ProcedureFactory.createProcedureRequest();
        ProcedureResponse existedProcedureResponse = procedureSteps.post(existedProcedureRequest, adminAuth);

        ProcedureRequest procedureToUpdate = ProcedureFactory.createProcedureRequest();
        ProcedureResponse actual = procedureSteps.put(
                existedProcedureResponse.getId(),
                procedureToUpdate,
                adminAuth,
                specifications.responseSpecificationV1(200),
                ProcedureResponse.class
        );
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();

        ProcedureResponse expected = ProcedureFactory.createProcedureResponse(actual.getId(), procedureToUpdate);
        assertThat(actual).isEqualTo(expected);

        ProcedureResponse existedProcedure = procedureSteps.get(
                actual.getId(),
                adminAuth,
                specifications.responseSpecificationV1(200),
                ProcedureResponse.class
        );
        assertThat(existedProcedure).isEqualTo(expected);

        procedureSteps.delete(actual.getId(), adminAuth);
    }

}
