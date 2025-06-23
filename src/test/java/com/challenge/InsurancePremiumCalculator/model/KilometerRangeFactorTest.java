package com.challenge.InsurancePremiumCalculator.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KilometerRangeFactorTest {

    @Test
    void testFactors() {
        assertEquals(0.5, KilometerRangeFactor.getFactor(3000));
        assertEquals(1.0, KilometerRangeFactor.getFactor(7000));
        assertEquals(1.5, KilometerRangeFactor.getFactor(15000));
        assertEquals(2.0, KilometerRangeFactor.getFactor(25000));
    }

    @Test
    void testBoundaryValues() {
        assertEquals(0.5, KilometerRangeFactor.getFactor(0));
        assertEquals(0.5, KilometerRangeFactor.getFactor(5000));
        assertEquals(1.0, KilometerRangeFactor.getFactor(5001));
        assertEquals(2.0, KilometerRangeFactor.getFactor(Integer.MAX_VALUE));
    }
}
