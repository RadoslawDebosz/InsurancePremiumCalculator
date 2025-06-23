package com.challenge.InsurancePremiumCalculator.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleTypeTest {

    @Test
    void testFactors() {
        assertEquals(1.0, VehicleType.CAR.getFactor());
        assertEquals(0.8, VehicleType.MOTORCYCLE.getFactor());
        assertEquals(1.5, VehicleType.TRUCK.getFactor());
    }
}
