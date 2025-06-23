package com.challenge.InsurancePremiumCalculator.model;

public class RegionCsvRow {
    private final String plz;
    private final String bundesland;
    private final String ort;

    public RegionCsvRow(String plz, String bundesland, String ort) {
        this.plz = plz;
        this.bundesland = bundesland;
        this.ort = ort;
    }

    public String getPlz() {
        return plz;
    }

    public String getBundesland() {
        return bundesland;
    }

    public String getOrt() {
        return ort;
    }
}
