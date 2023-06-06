package ru.zalimannard.api.institution.post;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.institution.InstitutionFactory;
import ru.zalimannard.api.institution.InstitutionRequest;
import ru.zalimannard.api.institution.InstitutionResponse;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Учреждение")
@Feature("Добавление")
@Story("Успешно")
class InstitutionPostCreatedTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешное добавление от ADMIN")
    void testCreateInstitution_AllCorrectDataByAdmin_Created() {
        InstitutionRequest institutionToCreate = InstitutionFactory.createInstitutionRequest();
        InstitutionResponse actual = institutionSteps.post(
                institutionToCreate,
                adminAuth,
                specifications.responseSpecificationV1(201),
                InstitutionResponse.class
        );
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();

        InstitutionResponse expected = InstitutionFactory.createInstitutionResponse(actual.getId(), institutionToCreate);
        assertThat(actual).isEqualTo(expected);

        InstitutionResponse existedInstitution = institutionSteps.get(
                actual.getId(),
                adminAuth,
                specifications.responseSpecificationV1(200),
                InstitutionResponse.class);
        assertThat(existedInstitution).isEqualTo(expected);

        institutionSteps.delete(actual.getId(), adminAuth);
    }

}
