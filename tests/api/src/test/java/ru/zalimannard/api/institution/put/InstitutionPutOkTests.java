package ru.zalimannard.api.institution.put;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.institution.InstitutionFactory;
import ru.zalimannard.api.institution.InstitutionRequest;
import ru.zalimannard.api.institution.InstitutionResponse;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Учреждение")
@Feature("Изменение")
@Story("Позитивные тесты")
class InstitutionPutOkTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешное изменение от ADMIN")
    void testPutInstitution_AllCorrectDataByAdmin_Ok() {
        InstitutionRequest existedInstitutionRequest = InstitutionFactory.createInstitutionRequest();
        InstitutionResponse existedInstitutionResponse = institutionSteps.post(existedInstitutionRequest, adminAuth);

        InstitutionRequest institutionToUpdate = InstitutionFactory.createInstitutionRequest();
        InstitutionResponse actual = institutionSteps.put(
                existedInstitutionResponse.getId(),
                institutionToUpdate,
                adminAuth,
                specifications.responseSpecificationV1(200),
                InstitutionResponse.class
        );
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();

        InstitutionResponse expected = InstitutionFactory.createInstitutionResponse(actual.getId(), institutionToUpdate);
        assertThat(actual).isEqualTo(expected);

        InstitutionResponse existedInstitution = institutionSteps.get(
                actual.getId(),
                adminAuth,
                specifications.responseSpecificationV1(200),
                InstitutionResponse.class
        );
        assertThat(existedInstitution).isEqualTo(expected);

        institutionSteps.delete(actual.getId(), adminAuth);
    }

}
