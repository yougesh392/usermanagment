package com.usmobile.usermanagment.utils;

import com.usmobile.usermanagment.model.User;

public class ValidationUtil {
    private static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^[0-9]{10}$");
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    private static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]*$");
    }

    public static void validateUser(User user) {
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (!isValidPhoneNumber(user.getPhoneNumber())) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        if (!isValidPassword(user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        if (!isValidName(user.getFirstName())) {
            throw new IllegalArgumentException("Invalid first name");
        }
        if (!isValidName(user.getLastName())) {
            throw new IllegalArgumentException("Invalid last name");
        }
    }
}
