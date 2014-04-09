package com.example.krumping;

public class Utils {

    private static final String APP_NAME = "Krumping.";

    public static String getTag(Class<?> c) {
        return APP_NAME + c.getSimpleName();
    }
}
