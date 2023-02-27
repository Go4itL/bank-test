package org.softparadigm.bankbackend.configuration.jwt;

public abstract class SecurityConstants {
    public static final String AUTH_HEADER = "Authorization";

    public static final String AUTH_AUDIENCE = "softparadigm";
    public static final long EXPIRATION_TIME = (5 * 60 * 60);

}
