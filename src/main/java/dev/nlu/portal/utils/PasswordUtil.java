package dev.nlu.portal.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for secure password hashing and checking using BCrypt.
 */
public class PasswordUtil {
    private static final int COST_FACTOR = 10;

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(COST_FACTOR));
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}