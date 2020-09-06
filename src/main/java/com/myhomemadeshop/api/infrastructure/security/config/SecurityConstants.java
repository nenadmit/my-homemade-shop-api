package com.myhomemadeshop.api.infrastructure.security.config;

public class SecurityConstants {

    public static final long EXPIRATION_TIME_IN_MINUTES = 240;
    public static final String SECRET = "secret";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static String[] getWhitelistedUrl() {
        return new String[]{"/register","/authenticate","/**"};
    }
}
