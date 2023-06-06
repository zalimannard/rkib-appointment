package ru.zalimannard.api.institution.post;

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
@Feature("Добавление")
@Story("Негативные тесты. Конфликт")
class InstitutionPostConflictTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление учреждения с занятым названием")
    void testCreateInstitution_duplicateName_Conflict() {
        InstitutionRequest institutionToCreate = InstitutionFactory.createInstitutionRequest();
        InstitutionResponse actual = institutionSteps.post(
                institutionToCreate,
                adminAuth,
                specifications.responseSpecificationV1(201),
                InstitutionResponse.class
        );

        ErrorResponse actual2 = institutionSteps.post(
                institutionToCreate,
                adminAuth,
                specifications.responseSpecificationV1(409),
                ErrorResponse.class
        );
        assertThat(actual2).isNotNull();

        institutionSteps.delete(actual.getId(), adminAuth);
    }

}
