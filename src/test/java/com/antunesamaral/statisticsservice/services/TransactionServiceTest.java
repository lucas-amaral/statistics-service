package com.antunesamaral.statisticsservice.services;

import com.antunesamaral.statisticsservice.entities.Transaction;
import com.antunesamaral.statisticsservice.entities.requests.TransactionRequestBody;
import com.antunesamaral.statisticsservice.entities.Statistic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionServiceTest {

    private TransactionService transactionService;

    private ConcurrentLinkedQueue<Transaction> transactions;

    private Statistic statistic;

    @BeforeEach
    void setUp() {
        transactions = new ConcurrentLinkedQueue<>();
        statistic = Statistic.empty();
        transactionService = new TransactionService(transactions, statistic);
    }

    @Test
    void saveTransaction_shouldReturnCreatedIfTransactionIsValid() {
        var amount = BigDecimal.ONE;
        var timestamp = LocalDateTime.now().minusSeconds(2);
        var transaction = new TransactionRequestBody(amount.toString(), timestamp.toString());

        assertThat(transactionService.saveTransaction(transaction))
                .isEqualTo(ResponseEntity.status(HttpStatus.CREATED).build());
        assertThat(transactions)
                .isNotEmpty()
                .containsOnly(new Transaction(amount, timestamp));
    }

    @Test
    void saveTransaction_shouldReturnBadRequestIfSomeFieldIsEmpty() {
        var amount = BigDecimal.ONE;
        var transaction = new TransactionRequestBody(amount.toString(), null);

        assertThat(transactionService.saveTransaction(transaction))
                .isEqualTo(ResponseEntity.badRequest().build());
        assertThat(transactions).isEmpty();
    }

    @Test
    void saveTransaction_shouldReturnNoContentIfTimestampIsBeforeLast1Minute() {
        var amount = BigDecimal.ONE;
        var timestamp = LocalDateTime.of(2023, 1, 1, 1,1,1);
        var transaction = new TransactionRequestBody(amount.toString(), timestamp.toString());

        assertThat(transactionService.saveTransaction(transaction))
                .isEqualTo(ResponseEntity.noContent().build());
        assertThat(transactions).isEmpty();
    }

    @Test
    void saveTransaction_shouldReturnUnprocessableEntityIfTimestampIsFuture() {
        var amount = BigDecimal.ONE;
        var timestamp = LocalDateTime.now().plusHours(1);
        var transaction = new TransactionRequestBody(amount.toString(), timestamp.toString());

        assertThat(transactionService.saveTransaction(transaction))
                .isEqualTo(ResponseEntity.unprocessableEntity().build());
        assertThat(transactions).isEmpty();
    }

    @Test
    void saveTransaction_shouldReturnUnprocessableEntityIfAmountIsNotParsable() {
        var timestamp = LocalDateTime.now().plusHours(1);
        var transaction = new TransactionRequestBody("fadfafdad", timestamp.toString());

        assertThat(transactionService.saveTransaction(transaction))
                .isEqualTo(ResponseEntity.unprocessableEntity().build());
        assertThat(transactions).isEmpty();
    }

    @Test
    void saveTransaction_shouldReturnUnprocessableEntityIfTimestampIsNotParsable() {
        var amount = BigDecimal.ONE;
        var transaction = new TransactionRequestBody(amount.toString(), "1/1/2021");

        assertThat(transactionService.saveTransaction(transaction))
                .isEqualTo(ResponseEntity.unprocessableEntity().build());
        assertThat(transactions).isEmpty();
    }


    @Test
    void deleteAllTransactions() {
        var amount = BigDecimal.ONE;
        var timestamp = LocalDateTime.now();
        transactions.add(new Transaction(amount, timestamp));
        transactions.add(new Transaction(amount, timestamp));
        transactions.add(new Transaction(amount, timestamp));

        assertThat(transactions).isNotEmpty();

        transactionService.deleteAllTransactions();

        assertThat(transactions).isEmpty();
    }
}