package com.usmobile.usermanagment.utils;

import java.util.Random;

public class RandomNumberGenerator {
    public static String generateRandomCTN() {
        Random random = new Random();
        String randomNumber = "";
        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            randomNumber += digit;
        }
        return randomNumber;
    }
}
