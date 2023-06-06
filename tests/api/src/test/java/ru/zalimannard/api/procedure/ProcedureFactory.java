package ru.zalimannard.api.procedure;

import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Set;

public class ProcedureFactory {

    private static final Faker faker = new Faker();
    private static final Set<String> uniqueProcedureNames = new HashSet<>();

    public static ProcedureRequest createProcedureRequest() {
        return ProcedureRequest.builder()
                .name(generateUniqueName())
                .build();
    }

    public static ProcedureResponse createProcedureResponse(String id, ProcedureRequest procedureRequest) {
        return ProcedureResponse.builder()
                .id(id)
                .name(procedureRequest.getName())
                .build();
    }

    private static String generateUniqueName() {
        String name;

        do {
            name = faker.name().title();
        } while (uniqueProcedureNames.contains(name));

        uniqueProcedureNames.add(name);
        return name;
    }

}
