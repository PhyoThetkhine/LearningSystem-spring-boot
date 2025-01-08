package com.system.Learning_system_springboot_angular.service;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

    private static final String ALL_CHARS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARS;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generatePassword() {
        // Define the minimum requirements for the password
        int minLength = 12; // Minimum password length
        int minLowercase = 1; // At least 1 lowercase letter
        int minUppercase = 1; // At least 1 uppercase letter
        int minDigits = 1; // At least 1 digit
        int minSpecialChars = 1; // At least 1 special character

        // Generate the password
        StringBuilder password = new StringBuilder();

        // Add at least one lowercase letter
        password.append(getRandomChar(LOWERCASE));

        // Add at least one uppercase letter
        password.append(getRandomChar(UPPERCASE));

        // Add at least one digit
        password.append(getRandomChar(DIGITS));

        // Add at least one special character
        password.append(getRandomChar(SPECIAL_CHARS));

        // Fill the remaining length with random characters
        for (int i = password.length(); i < minLength; i++) {
            password.append(getRandomChar(ALL_CHARS));
        }

        // Shuffle the password to ensure randomness
        List<Character> passwordChars = password.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(passwordChars, RANDOM);

        return passwordChars.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private static char getRandomChar(String charSet) {
        int randomIndex = RANDOM.nextInt(charSet.length());
        return charSet.charAt(randomIndex);
    }

}