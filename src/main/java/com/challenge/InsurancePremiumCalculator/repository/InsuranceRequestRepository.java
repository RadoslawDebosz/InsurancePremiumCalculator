package com.challenge.InsurancePremiumCalculator.repository;

import com.challenge.InsurancePremiumCalculator.entity.InsuranceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRequestRepository extends JpaRepository<InsuranceRequest, Long> {
}

