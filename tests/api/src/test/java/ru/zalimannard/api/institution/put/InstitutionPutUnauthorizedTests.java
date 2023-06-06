package ru.zalimannard.api.institution.put;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.institution.InstitutionFactory;
import ru.zalimannard.api.institution.InstitutionRequest;

@Epic("Учреждение")
@Feature("Изменение")
@Story("Негативные тесты. Запрос без авторизации")
class InstitutionPutUnauthorizedTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Без авторизации")
    void testPutInstitution_AllCorrectData_Unauthorized() {
        InstitutionRequest institutionToCreate = InstitutionFactory.createInstitutionRequest();
        institutionSteps.put(
                Faker.instance().internet().uuid(),
                institutionToCreate,
                null,
                specifications.responseSpecificationV1(401),
                null
        );
    }

}
