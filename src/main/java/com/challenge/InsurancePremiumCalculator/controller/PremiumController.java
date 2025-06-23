package com.challenge.InsurancePremiumCalculator.controller;

import com.challenge.InsurancePremiumCalculator.entity.InsuranceRequest;
import com.challenge.InsurancePremiumCalculator.model.PremiumRequest;
import com.challenge.InsurancePremiumCalculator.repository.InsuranceRequestRepository;
import com.challenge.InsurancePremiumCalculator.service.PremiumCalculatorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/premium")
public class PremiumController {

    private final PremiumCalculatorService calculatorService;
    private final InsuranceRequestRepository repository;

    public PremiumController(PremiumCalculatorService calculatorService, InsuranceRequestRepository repository) {
        this.calculatorService = calculatorService;
        this.repository = repository;
    }

    @PostMapping("/calculate")
    public double calculatePremium(@RequestBody PremiumRequest request) {
        return calculatorService.calculateAndSave(
                request.getKilometersPerYear(),
                request.getVehicleType(),
                request.getPlz()
        );
    }

    @GetMapping("/all-requests")
    public List<InsuranceRequest> getAllRequests() {
        return repository.findAll();
    }
}
