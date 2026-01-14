package dev.nlu.portal.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    private final static int WORKLOAD = 10;

    public static String hashpw(String password) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean verify(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
