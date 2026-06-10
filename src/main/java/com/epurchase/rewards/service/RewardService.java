package com.epurchase.rewards.service;

import com.epurchase.rewards.dto.RewardResponseDto;

/**
 * Service layer for rewards.
 */
public interface RewardService {

    RewardResponseDto getRewardsByCustomer(Long customerId);
}