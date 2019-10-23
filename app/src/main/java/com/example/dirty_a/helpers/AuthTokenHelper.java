package com.example.dirty_a.helpers;

public class AuthTokenHelper {

    private static String authToken = "";

    public static String getAuthToken() {
        return authToken.length() > 0 ? authToken : null;
    }

    public static void setAuthToken(String newAuthToken) {
        authToken = newAuthToken;
    }
}
