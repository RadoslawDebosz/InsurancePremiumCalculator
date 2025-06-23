package com.challenge.InsurancePremiumCalculator.model;

public class KilometerRangeFactor {
    public static double getFactor(int kilometersPerYear) {
        if (kilometersPerYear <= 5000) return 0.5;
        else if (kilometersPerYear <= 10000) return 1.0;
        else if (kilometersPerYear <= 20000) return 1.5;
        else return 2.0;
    }
}
