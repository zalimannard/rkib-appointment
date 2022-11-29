package ru.zalimannard.bachelorthesisserver.entities;

public abstract class Entity {
    private final Integer id;

    public Entity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
