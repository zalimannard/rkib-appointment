package ru.zalimannard.api.procedure.put;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.errors.ErrorResponse;
import ru.zalimannard.api.procedure.ProcedureFactory;
import ru.zalimannard.api.procedure.ProcedureRequest;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Услуга")
@Feature("Изменение")
@Story("Негативные тесты. Не найдено")
class ProcedurePutNotFoundTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Не найдено")
    void testPutProcedure_invalidName_NotFound() {
        ProcedureRequest procedureToCreate = ProcedureFactory.createProcedureRequest();
        ErrorResponse actual = procedureSteps.put(
                Faker.instance().internet().uuid(),
                procedureToCreate,
                adminAuth,
                specifications.responseSpecificationV1(404),
                ErrorResponse.class
        );
        assertThat(actual).isNotNull();
    }

}
