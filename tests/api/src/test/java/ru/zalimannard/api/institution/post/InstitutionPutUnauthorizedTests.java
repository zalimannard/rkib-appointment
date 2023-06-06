package ru.zalimannard.api.institution.post;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.institution.InstitutionFactory;
import ru.zalimannard.api.institution.InstitutionRequest;

@Epic("Учреждение")
@Feature("Добавление")
@Story("Негативные тесты. Запрос без авторизации")
class InstitutionPutUnauthorizedTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Без авторизации")
    void testCreateInstitution_AllCorrectData_Unauthorized() {
        InstitutionRequest institutionToCreate = InstitutionFactory.createInstitutionRequest();
        institutionSteps.post(
                institutionToCreate,
                null,
                specifications.responseSpecificationV1(401),
                null
        );
    }

}
