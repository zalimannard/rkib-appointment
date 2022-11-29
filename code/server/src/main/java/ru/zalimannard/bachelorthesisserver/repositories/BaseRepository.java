package ru.zalimannard.bachelorthesisserver.repositories;

import ru.zalimannard.bachelorthesisserver.entities.Entity;

import java.util.List;

public interface BaseRepository<T extends Entity> {
    void create(T entity);

    List<T> readAll();

    T read(int id);

    void update(int id, T entity);

    void delete(int id);
}
