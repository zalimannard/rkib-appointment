package ru.zalimannard.api.person;

import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Set;

public class PersonFactory {

    private static final Faker faker = new Faker();
    private static final Set<String> uniqueUsernames = new HashSet<>();

    public static PersonRequest createPersonRequest() {
        return PersonRequest.builder()
                .username(generateUniqueUsername())
                .password(faker.internet().password(8, 32))
                .lastName(faker.name().lastName())
                .firstName(faker.name().firstName())
                .patronymic(faker.name().nameWithMiddle())
                .build();
    }

    public static PersonResponse createPersonResponse(String id, PersonRequest personRequest) {
        return PersonResponse.builder()
                .id(id)
                .username(personRequest.getUsername())
                .lastName(personRequest.getLastName())
                .firstName(personRequest.getFirstName())
                .patronymic(personRequest.getPatronymic())
                .build();
    }

    private static String generateUniqueUsername() {
        String username;

        do {
            username = faker.name().username();
        } while (uniqueUsernames.contains(username));

        uniqueUsernames.add(username);
        return username;
    }

}
