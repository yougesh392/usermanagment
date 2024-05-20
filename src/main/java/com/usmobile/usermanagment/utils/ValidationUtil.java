package com.usmobile.usermanagment.utils;

import com.usmobile.usermanagment.model.UpdateUserRequest;
import com.usmobile.usermanagment.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

    public static void validateUser(UserDTO user) {
        if (user.getEmail()== null || !isValidEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email cannot be empty or invalid");
        }
        if (user.getPhoneNumber() == null || !isValidPhoneNumber(user.getPhoneNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "phone number cannot be empty or invalid");
        }
        if (user.getPassword() == null || !isValidPassword(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password cannot be empty or less than 8 characters");
        }
        if (user.getFirstName() == null || !isValidName(user.getFirstName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "first name cannot be empty or invalid");
        }
        if (user.getLastName() == null || !isValidName(user.getLastName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "last cannot be empty or invalid");
        }
    }
    public static void validateUpdateUser(UpdateUserRequest user) {
        if (user.getEmail()== null || !isValidEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email cannot be empty or invalid");
        }
        if (user.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserId cannot be empty or invalid");
        }
        if (user.getFirstName() == null || !isValidName(user.getFirstName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "first name cannot be empty or invalid");
        }
        if (user.getLastName() == null || !isValidName(user.getLastName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "last cannot be empty or invalid");
        }
    }
}
