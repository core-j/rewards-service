package com.epurchase.rewards.controller;

import com.epurchase.rewards.dto.RewardResponseDto;
import com.epurchase.rewards.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for reward APIs.
 */
@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    /**
     * Get rewards by customer id.
     */
    @GetMapping("/{customerId}")
    public RewardResponseDto getRewards(
            @PathVariable Long customerId) {

        return rewardService
                .getRewardsByCustomer(customerId);
    }
}