package ru.zalimannard.bachelorthesisserver.favor;

import java.util.List;

public interface FavorService {

    FavorDto get(String id);

    List<FavorDto> list();
}
