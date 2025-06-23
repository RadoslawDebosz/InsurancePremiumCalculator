package com.challenge.InsurancePremiumCalculator.model;

public enum VehicleType {
    CAR(1.0),
    MOTORCYCLE(0.8),
    TRUCK(1.5);

    private final double factor;

    VehicleType(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}

