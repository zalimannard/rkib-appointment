package ru.zalimannard.bachelorthesisserver.repositories;

import ru.zalimannard.bachelorthesisserver.entities.Entity;

import java.util.List;

public interface BaseRepository<T extends Entity> {
    List<T> select();
    T select(Integer id);
    T insert(T entity);
    T update(Integer id, T entity);
    T delete(Integer id);
}
