package ru.zalimannard.rkibappointmentbackend.security;

public abstract class AuthDataConstructor {

    public static String construct(String username, String password) {
        return username + ":" + password;
    }

}
