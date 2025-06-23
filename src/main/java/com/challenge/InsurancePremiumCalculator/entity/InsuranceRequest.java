package com.challenge.InsurancePremiumCalculator.entity;

import com.challenge.InsurancePremiumCalculator.model.VehicleType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class InsuranceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int kilometersPerYear;

    private String plz;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private double calculatedPremium;

    private LocalDateTime requestTime;

    public InsuranceRequest() {
    }

    public InsuranceRequest(int kilometersPerYear, String plz, VehicleType vehicleType, double calculatedPremium) {
        this.kilometersPerYear = kilometersPerYear;
        this.plz = plz;
        this.vehicleType = vehicleType;
        this.calculatedPremium = calculatedPremium;
        this.requestTime = LocalDateTime.now();
    }


    public Long getId() {
        return id;
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

    public double getCalculatedPremium() {
        return calculatedPremium;
    }

    public void setCalculatedPremium(double calculatedPremium) {
        this.calculatedPremium = calculatedPremium;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}
