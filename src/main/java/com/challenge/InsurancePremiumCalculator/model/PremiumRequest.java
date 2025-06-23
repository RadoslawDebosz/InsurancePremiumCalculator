package com.challenge.InsurancePremiumCalculator.model;

public class PremiumRequest {
    private int kilometersPerYear;
    private String plz;
    private VehicleType vehicleType;

    public PremiumRequest(int kilometersPerYear, String plz, VehicleType vehicleType) {
        this.kilometersPerYear = kilometersPerYear;
        this.plz = plz;
        this.vehicleType = vehicleType;
    }

    public PremiumRequest() {
    }

    public int getKilometersPerYear() {
        return kilometersPerYear;
    }

    public void setKilometersPerYear(int kilometersPerYear) {
        this.kilometersPerYear = kilometersPerYear;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
