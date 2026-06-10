package com.epurchase.rewards.service;

import com.epurchase.rewards.dto.RewardResponseDto;
import com.epurchase.rewards.entity.Transaction;
import com.epurchase.rewards.repository.TransactionRepository;
import com.epurchase.rewards.serviceImpl.RewardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for RewardServiceImpl.
 */
class RewardServiceImplTest {

    private TransactionRepository transactionRepository;

    private RewardServiceImpl rewardService;

    @BeforeEach
    void setUp() {

        transactionRepository =
                Mockito.mock(TransactionRepository.class);

        rewardService =
                new RewardServiceImpl(transactionRepository);
    }

    @Test
    void shouldCalculateRewardsCorrectly() {

        List<Transaction> transactions = List.of(

                Transaction.builder()
                        .id(1L)
                        .customerId(1L)
                        .customerName("Chaithanya")
                        .amount(120.0)
                        .transactionDate(
                                LocalDate.of(2026, 4, 10))
                        .build(),

                Transaction.builder()
                        .id(2L)
                        .customerId(1L)
                        .customerName("Chaithanya")
                        .amount(75.0)
                        .transactionDate(
                                LocalDate.of(2026, 5, 15))
                        .build()
        );

        when(transactionRepository
                .findByCustomerId(1L))
                .thenReturn(transactions);

        RewardResponseDto response =
                rewardService.getRewardsByCustomer(1L);

        assertEquals(115,
                response.getTotalRewards());

        assertEquals("Chaithanya",
                response.getCustomerName());
    }

    @Test
    void shouldReturnZeroForNegativeAmount() {

        List<Transaction> transactions = List.of(

                Transaction.builder()
                        .id(1L)
                        .customerId(1L)
                        .customerName("Chaithanya")
                        .amount(-50.0)
                        .transactionDate(
                                LocalDate.now())
                        .build()
        );

        when(transactionRepository
                .findByCustomerId(1L))
                .thenReturn(transactions);

        RewardResponseDto response =
                rewardService.getRewardsByCustomer(1L);

        assertEquals(0,
                response.getTotalRewards());
    }
}
