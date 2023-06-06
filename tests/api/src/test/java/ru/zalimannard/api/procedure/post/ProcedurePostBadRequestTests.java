package ru.zalimannard.api.procedure.post;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.errors.ErrorResponse;
import ru.zalimannard.api.procedure.ProcedureFactory;
import ru.zalimannard.api.procedure.ProcedureRequest;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Услуга")
@Feature("Добавление")
@Story("Негативные тесты. Неверный запрос")
class ProcedurePostBadRequestTests extends BaseTest {

    @ParameterizedTest
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Некорректное название учреждения")
    @NullSource
    @ValueSource(strings = {
            "",
            " ",
            "   ",
    })
    void testCreateProcedure_invalidName_BadRequest(String name) {
        ProcedureRequest procedureToCreate = ProcedureFactory.createProcedureRequest().toBuilder()
                .name(name)
                .build();
        ErrorResponse actual = procedureSteps.post(
                procedureToCreate,
                adminAuth,
                specifications.responseSpecificationV1(400),
                ErrorResponse.class
        );
        assertThat(actual).isNotNull();
    }

}
