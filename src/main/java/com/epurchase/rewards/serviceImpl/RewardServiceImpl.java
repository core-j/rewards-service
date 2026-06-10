package com.epurchase.rewards.serviceImpl;

import com.epurchase.rewards.dto.RewardResponseDto;
import com.epurchase.rewards.entity.Transaction;
import com.epurchase.rewards.exception.CustomerNotFoundException;
import com.epurchase.rewards.repository.TransactionRepository;
import com.epurchase.rewards.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Service implementation for reward calculations.
 */
@Service
@RequiredArgsConstructor
public class RewardServiceImpl
        implements RewardService {

    private final TransactionRepository transactionRepository;

    @Override
    public RewardResponseDto getRewardsByCustomer(
            Long customerId) {

        List<Transaction> transactions =
                transactionRepository
                        .findByCustomerId(customerId);

        if (transactions.isEmpty()) {
            throw new CustomerNotFoundException(
                    "Customer not found with id: "
                            + customerId);
        }

        Map<String, Integer> monthlyRewards =
                new LinkedHashMap<>();

        int totalRewards = 0;

        for (Transaction transaction : transactions) {

            int rewardPoints =
                    calculateRewardPoints(
                            transaction.getAmount());

            String month =
                    transaction.getTransactionDate()
                            .getMonth()
                            .toString();

            monthlyRewards.put(
                    month,
                    monthlyRewards.getOrDefault(
                            month,
                            0
                    ) + rewardPoints
            );

            totalRewards += rewardPoints;
        }

        Transaction firstTransaction =
                transactions.get(0);

        return RewardResponseDto.builder()
                .customerId(
                        firstTransaction.getCustomerId())
                .customerName(
                        firstTransaction.getCustomerName())
                .monthlyRewards(monthlyRewards)
                .totalRewards(totalRewards)
                .build();
    }

    /**
     * Calculates reward points.
     */
    private int calculateRewardPoints(
            Double amount) {

        if (amount == null || amount < 0) {
            return 0;
        }

        if (amount <= 50) {
            return 0;
        }

        if (amount <= 100) {
            return (int) (amount - 50);
        }

        return (int) (
                50 + ((amount - 100) * 2)
        );
    }
}