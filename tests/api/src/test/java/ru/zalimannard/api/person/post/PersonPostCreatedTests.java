package ru.zalimannard.api.person.post;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.zalimannard.api.BaseTest;
import ru.zalimannard.api.person.PersonFactory;
import ru.zalimannard.api.person.PersonRequest;
import ru.zalimannard.api.person.PersonResponse;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Человек")
@Feature("Добавление")
@Story("Успешно")
class PersonPostCreatedTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешное добавление со всеми данными от ADMIN")
    void testCreatePerson_AllCorrectDataByAdmin_Created() {
        PersonRequest personToCreate = PersonFactory.createPersonRequest();
        PersonResponse actual = personSteps.post(
                personToCreate,
                adminAuth,
                specifications.responseSpecificationV1(201),
                PersonResponse.class
        );
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();

        PersonResponse expected = PersonFactory.createPersonResponse(actual.getId(), personToCreate);
        assertThat(actual).isEqualTo(expected);

        PersonResponse existedPerson = personSteps.get(
                actual.getId(),
                adminAuth,
                specifications.responseSpecificationV1(200),
                PersonResponse.class);
        assertThat(existedPerson).isEqualTo(expected);

        personSteps.delete(actual.getId(), adminAuth);
    }

}
