package com.challenge.InsurancePremiumCalculator.service;

import com.challenge.InsurancePremiumCalculator.model.RegionCsvRow;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegionService {

    private final Map<String, RegionCsvRow> postcodeMap = new HashMap<>();

    @PostConstruct
    public void init() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/postcodes.csv"), StandardCharsets.UTF_8))) {

            reader.lines().skip(1).forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    String plz = parts[6].replace("\"", "").trim();         // POSTLEITZAHL
                    String bundesland = parts[2].replace("\"", "").trim(); // REGION1
                    String ort = parts[7].replace("\"", "").trim();        // ORT

                    postcodeMap.put(plz, new RegionCsvRow(plz, bundesland, ort));
                }
            });

            System.out.println("RegionService geladen: " + postcodeMap.size() + " Einträge.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegionCsvRow getRegionByPlz(String plz) {
        return postcodeMap.get(plz);
    }

    public double getRegionFactor(String plz) {
        RegionCsvRow row = postcodeMap.get(plz);
        if (row == null) return 1.0;

        return switch (row.getBundesland().toLowerCase()) {
            case "bayern", "baden-württemberg" -> 1.2;
            case "berlin", "hamburg" -> 1.1;
            case "sachsen", "thüringen" -> 0.9;
            default -> 1.0;
        };
    }
}
