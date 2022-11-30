package ru.zalimannard.bachelorthesisserver.repositories;

import java.util.List;

public interface BaseRepository<Entity> {
    void create(Entity entity);

    Entity retrieve(int id);

    List<Entity> retrieveAll();

    void update(Entity entity);

    void delete(int id);
}
