package by.it.academy.justshooter.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordAuthentication {

    private static String salt = BCrypt.gensalt();

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword (String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static void main(String[] args) {
        String password = "admin";
        String candidate = "admin1";

        String hashed = hashPassword(password);

        if (checkPassword(candidate, hashed))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
    }

}
