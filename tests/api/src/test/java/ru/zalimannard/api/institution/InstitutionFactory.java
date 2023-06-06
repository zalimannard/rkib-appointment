package ru.zalimannard.api.institution;

import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Set;

public class InstitutionFactory {

    private static final Faker faker = new Faker();
    private static final Set<String> uniqueInstitutionNames = new HashSet<>();

    public static InstitutionRequest createInstitutionRequest() {
        return InstitutionRequest.builder()
                .name(generateUniqueName())
                .build();
    }

    public static InstitutionResponse createInstitutionResponse(String id, InstitutionRequest institutionRequest) {
        return InstitutionResponse.builder()
                .id(id)
                .name(institutionRequest.getName())
                .build();
    }

    private static String generateUniqueName() {
        String name;

        do {
            name = faker.name().title();
        } while (uniqueInstitutionNames.contains(name));

        uniqueInstitutionNames.add(name);
        return name;
    }

}
