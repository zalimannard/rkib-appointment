package ru.zalimannard.api.institution.put;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.errors.ErrorResponse;
import ru.zalimannard.api.institution.InstitutionFactory;
import ru.zalimannard.api.institution.InstitutionRequest;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Учреждение")
@Feature("Изменение")
@Story("Негативные тесты. Не найдено")
class InstitutionPutNotFoundTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Не найдено")
    void testPutInstitution_invalidName_NotFound() {
        InstitutionRequest institutionToCreate = InstitutionFactory.createInstitutionRequest();
        ErrorResponse actual = institutionSteps.put(
                Faker.instance().internet().uuid(),
                institutionToCreate,
                adminAuth,
                specifications.responseSpecificationV1(404),
                ErrorResponse.class
        );
        assertThat(actual).isNotNull();
    }

}
