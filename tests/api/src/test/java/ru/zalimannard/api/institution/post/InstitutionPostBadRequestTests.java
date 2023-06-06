package ru.zalimannard.api.institution.post;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.errors.ErrorResponse;
import ru.zalimannard.api.institution.InstitutionFactory;
import ru.zalimannard.api.institution.InstitutionRequest;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Учреждение")
@Feature("Добавление")
@Story("Негативные тесты. Неверный запрос")
class InstitutionPostBadRequestTests extends BaseTest {

    @ParameterizedTest
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Некорректное название учреждения")
    @NullSource
    @ValueSource(strings = {
            "",
            " ",
            "   ",
    })
    void testCreateInstitution_invalidName_BadRequest(String name) {
        InstitutionRequest institutionToCreate = InstitutionFactory.createInstitutionRequest().toBuilder()
                .name(name)
                .build();
        ErrorResponse actual = institutionSteps.post(
                institutionToCreate,
                adminAuth,
                specifications.responseSpecificationV1(400),
                ErrorResponse.class
        );
        assertThat(actual).isNotNull();
    }

}
