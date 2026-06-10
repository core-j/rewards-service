package com.epurchase.rewards.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for RewardController.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RewardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnRewardsSuccessfully()
            throws Exception {

        mockMvc.perform(
                        get("/api/rewards/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$.customerName")
                        .value("John"));
    }

    @Test
    void shouldReturn404ForInvalidCustomer()
            throws Exception {

        mockMvc.perform(
                        get("/api/rewards/999"))
                .andExpect(status().isNotFound());
    }
}
