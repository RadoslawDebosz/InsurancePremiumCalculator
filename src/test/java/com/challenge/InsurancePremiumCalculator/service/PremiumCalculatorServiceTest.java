package com.challenge.InsurancePremiumCalculator.service;

import com.challenge.InsurancePremiumCalculator.entity.InsuranceRequest;
import com.challenge.InsurancePremiumCalculator.model.RegionCsvRow;
import com.challenge.InsurancePremiumCalculator.model.VehicleType;
import com.challenge.InsurancePremiumCalculator.repository.InsuranceRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PremiumCalculatorServiceTest {

    @Mock
    private RegionService regionService;

    @Mock
    private InsuranceRequestRepository repository;

    @InjectMocks
    private PremiumCalculatorService service;

    @Test
    void shouldCalculatePremiumCorrectly() {
        // GIVEN
        String plz = "10115";
        int kilometers = 15000;
        VehicleType vehicleType = VehicleType.CAR;

        RegionCsvRow region = new RegionCsvRow(plz, "Berlin", "Berlin");

        when(regionService.getRegionByPlz(plz)).thenReturn(region);
        when(regionService.getRegionFactor(plz)).thenReturn(1.1);

        // WHEN
        double result = service.calculateAndSave(kilometers, vehicleType, plz);

        // THEN
        double expected = 1.5 * 1.0 * 1.1; // kmFactor * vehicleFactor * regionFactor
        assertEquals(expected, result, 0.001);

        verify(repository).save(any(InsuranceRequest.class));
    }

    @Test
    void shouldThrowExceptionForInvalidPLZ() {
        // GIVEN
        String invalidPlz = "99999";
        when(regionService.getRegionByPlz(invalidPlz)).thenReturn(null);

        // WHEN / THEN
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                service.calculateAndSave(10000, VehicleType.CAR, invalidPlz));

        assertTrue(exception.getMessage().contains("Invalid postal code"));
    }

    @Test
    void shouldThrowExceptionForNegativeKilometers() {
        // GIVEN
        String plz = "10115";

        // WHEN / THEN
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                service.calculateAndSave(-5000, VehicleType.CAR, plz));

        assertTrue(exception.getMessage().contains("Kilometers per year must be positive"));
    }
}
