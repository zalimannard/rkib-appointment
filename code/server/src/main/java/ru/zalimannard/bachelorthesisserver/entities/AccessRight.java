package ru.zalimannard.bachelorthesisserver.entities;

public class AccessRight extends Entity {
    private final String accessRightName;

    public AccessRight(Integer id, String accessRightName) {
        super(id);
        this.accessRightName = accessRightName;
    }

    public String getAccessRightName() {
        return accessRightName;
    }
}
