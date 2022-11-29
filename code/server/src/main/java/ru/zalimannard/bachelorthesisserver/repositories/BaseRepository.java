package ru.zalimannard.bachelorthesisserver.repositories;

import ru.zalimannard.bachelorthesisserver.entities.Entity;

import java.util.List;

public interface BaseRepository<T extends Entity> {
    void create(T entity);
    List<T> read();
    T read(int id);
    boolean update(int id, T entity);
    boolean delete(int id);
}
