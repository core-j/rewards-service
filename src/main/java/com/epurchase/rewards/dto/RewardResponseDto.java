package com.epurchase.rewards.dto;


import lombok.*;

import java.util.Map;

/**
 * DTO for reward response.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardResponseDto {

    private Long customerId;

    private String customerName;

    private Map<String, Integer> monthlyRewards;

    private Integer totalRewards;
}