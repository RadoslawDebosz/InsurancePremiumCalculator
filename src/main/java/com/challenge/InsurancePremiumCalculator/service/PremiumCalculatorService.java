package com.challenge.InsurancePremiumCalculator.service;

import com.challenge.InsurancePremiumCalculator.entity.InsuranceRequest;
import com.challenge.InsurancePremiumCalculator.model.KilometerRangeFactor;
import com.challenge.InsurancePremiumCalculator.model.RegionCsvRow;
import com.challenge.InsurancePremiumCalculator.model.VehicleType;
import com.challenge.InsurancePremiumCalculator.repository.InsuranceRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class PremiumCalculatorService {

    private final RegionService regionService;
    private final InsuranceRequestRepository repository;

    public PremiumCalculatorService(RegionService regionService, InsuranceRequestRepository repository) {
        this.regionService = regionService;
        this.repository = repository;
    }

    public double calculateAndSave(int kilometers, VehicleType type, String plz) {

        if (kilometers <= 0) {
            throw new IllegalArgumentException("Kilometers per year must be positive.");
        }

        RegionCsvRow region = regionService.getRegionByPlz(plz);
        if (region == null) {
            throw new IllegalArgumentException("Invalid postal code: no matching region found.");
        }

        double kmFactor = KilometerRangeFactor.getFactor(kilometers);
        double vehicleFactor = type.getFactor();
        double regionFactor = regionService.getRegionFactor(plz);

        double premium = kmFactor * vehicleFactor * regionFactor;

        repository.save(new InsuranceRequest(kilometers, plz, type, premium));

        return premium;
    }
}
