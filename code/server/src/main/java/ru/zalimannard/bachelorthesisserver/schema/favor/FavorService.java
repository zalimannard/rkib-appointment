package ru.zalimannard.bachelorthesisserver.schema.favor;

import java.util.List;

public interface FavorService {

    FavorDto get(String id);

    List<FavorDto> list(FavorDto exampleFavorDto);
}
