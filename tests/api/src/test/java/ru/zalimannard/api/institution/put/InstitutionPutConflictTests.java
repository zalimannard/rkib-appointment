package ru.zalimannard.api.institution.put;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.errors.ErrorResponse;
import ru.zalimannard.api.institution.InstitutionFactory;
import ru.zalimannard.api.institution.InstitutionRequest;
import ru.zalimannard.api.institution.InstitutionResponse;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Учреждение")
@Feature("Изменение")
@Story("Негативные тесты. Конфликт")
class InstitutionPutConflictTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление учреждения с занятым названием")
    void testPutInstitution_duplicateName_Conflict() {
        InstitutionRequest existedInstitutionRequest = InstitutionFactory.createInstitutionRequest();
        institutionSteps.post(existedInstitutionRequest, adminAuth);

        InstitutionRequest institutionToCreate = InstitutionFactory.createInstitutionRequest();
        InstitutionResponse actual = institutionSteps.post(institutionToCreate, adminAuth);

        ErrorResponse actual2 = institutionSteps.put(
                actual.getId(),
                existedInstitutionRequest,
                adminAuth,
                specifications.responseSpecificationV1(409),
                ErrorResponse.class
        );
        assertThat(actual2).isNotNull();

        institutionSteps.delete(actual.getId(), adminAuth);
    }

}
