package ru.zalimannard.api.procedure.put;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.errors.ErrorResponse;
import ru.zalimannard.api.procedure.ProcedureFactory;
import ru.zalimannard.api.procedure.ProcedureRequest;
import ru.zalimannard.api.procedure.ProcedureResponse;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Услуга")
@Feature("Изменение")
@Story("Негативные тесты. Неверный запрос")
class ProcedurePutBadRequestTests extends BaseTest {

    @ParameterizedTest
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Некорректное название услуги")
    @NullSource
    @ValueSource(strings = {
            "",
            " ",
            "   ",
    })
    void testPutProcedure_invalidName_BadRequest(String name) {
        ProcedureRequest existedProcedureRequest = ProcedureFactory.createProcedureRequest();
        ProcedureResponse existedProcedureResponse = procedureSteps.post(existedProcedureRequest, adminAuth);

        ProcedureRequest procedureToCreate = ProcedureFactory.createProcedureRequest().toBuilder()
                .name(name)
                .build();
        ErrorResponse actual = procedureSteps.put(
                existedProcedureResponse.getId(),
                procedureToCreate,
                adminAuth,
                specifications.responseSpecificationV1(400),
                ErrorResponse.class
        );
        assertThat(actual).isNotNull();
    }

}
