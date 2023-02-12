package ru.zalimannard.bachelorthesisserver.favor;

import java.util.List;

public interface FavorService {

    FavorDto get(int id);

    List<FavorDto> list();
}
