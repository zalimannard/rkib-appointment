package ru.zalimannard.api.procedure.post;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.procedure.ProcedureFactory;
import ru.zalimannard.api.procedure.ProcedureRequest;
import ru.zalimannard.api.procedure.ProcedureResponse;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Услуга")
@Feature("Добавление")
@Story("Успешно")
class ProcedurePostCreatedTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешное добавление от ADMIN")
    void testCreateProcedure_AllCorrectDataByAdmin_Created() {
        ProcedureRequest procedureToCreate = ProcedureFactory.createProcedureRequest();
        ProcedureResponse actual = procedureSteps.post(
                procedureToCreate,
                adminAuth,
                specifications.responseSpecificationV1(201),
                ProcedureResponse.class
        );
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();

        ProcedureResponse expected = ProcedureFactory.createProcedureResponse(actual.getId(), procedureToCreate);
        assertThat(actual).isEqualTo(expected);

        ProcedureResponse existedProcedure = procedureSteps.get(
                actual.getId(),
                adminAuth,
                specifications.responseSpecificationV1(200),
                ProcedureResponse.class);
        assertThat(existedProcedure).isEqualTo(expected);

        procedureSteps.delete(actual.getId(), adminAuth);
    }

}
