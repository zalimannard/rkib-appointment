package ru.zalimannard.api.institution.put;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.errors.ErrorResponse;
import ru.zalimannard.api.institution.InstitutionFactory;
import ru.zalimannard.api.institution.InstitutionRequest;
import ru.zalimannard.api.institution.InstitutionResponse;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Учреждение")
@Feature("Изменение")
@Story("Негативные тесты. Неверный запрос")
class InstitutionPutBadRequestTests extends BaseTest {

    @ParameterizedTest
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Некорректное название учреждения")
    @NullSource
    @ValueSource(strings = {
            "",
            " ",
            "   ",
    })
    void testPutInstitution_invalidName_BadRequest(String name) {
        InstitutionRequest existedInstitutionRequest = InstitutionFactory.createInstitutionRequest();
        InstitutionResponse existedInstitutionResponse = institutionSteps.post(existedInstitutionRequest, adminAuth);

        InstitutionRequest institutionToCreate = InstitutionFactory.createInstitutionRequest().toBuilder()
                .name(name)
                .build();
        ErrorResponse actual = institutionSteps.put(
                existedInstitutionResponse.getId(),
                institutionToCreate,
                adminAuth,
                specifications.responseSpecificationV1(400),
                ErrorResponse.class
        );
        assertThat(actual).isNotNull();
    }

}
