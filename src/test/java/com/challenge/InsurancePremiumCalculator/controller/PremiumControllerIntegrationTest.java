package com.challenge.InsurancePremiumCalculator.controller;

import com.challenge.InsurancePremiumCalculator.model.PremiumRequest;
import com.challenge.InsurancePremiumCalculator.model.VehicleType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PremiumControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void calculatePremium_validRequest_returnsPremium() throws Exception {
        PremiumRequest request = new PremiumRequest(12000, "10115", VehicleType.CAR);

        mockMvc.perform(post("/api/premium/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void calculatePremium_invalidKilometers_returnsBadRequest() throws Exception {
        PremiumRequest request = new PremiumRequest(-500, "10115", VehicleType.CAR);

        mockMvc.perform(post("/api/premium/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void calculatePremium_invalidPlz_returnsBadRequest() throws Exception {
        PremiumRequest request = new PremiumRequest(8000, "99999", VehicleType.TRUCK);

        mockMvc.perform(post("/api/premium/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllRequests_returnsList() throws Exception {
        mockMvc.perform(get("/api/premium/all-requests"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].id").exists());
    }
}

