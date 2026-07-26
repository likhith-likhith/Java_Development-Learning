package com.likhith.reservation.util;

import java.util.Random;

public class PNRGenerator {

    private static final Random random = new Random();

    public static String generatePNR() {

        int number = 100000 + random.nextInt(900000);

        return "PNR" + number;
    }
}