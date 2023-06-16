package com.antunesamaral.statisticsservice.services;

import com.antunesamaral.statisticsservice.entities.Statistic;
import com.antunesamaral.statisticsservice.entities.Transaction;
import com.antunesamaral.statisticsservice.entities.requests.TransactionRequestBody;
import com.antunesamaral.statisticsservice.entities.responses.StatisticResponseBody;
import com.antunesamaral.statisticsservice.utils.DateTimeUtils;
import com.antunesamaral.statisticsservice.utils.MathUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toCollection;

@Service
@EnableAsync
public class TransactionService {
    private static final int ONE_SECOND = 1000;
    private static final int THREAD_POOL_SIZE = 10;
    private ConcurrentLinkedQueue<Transaction> transactions;

    private Statistic statistic;

    private final ExecutorService executorService;

    public TransactionService(ConcurrentLinkedQueue<Transaction> transactions, Statistic statistic) {
        this.transactions = transactions;
        this.statistic = statistic;
        this.executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public ResponseEntity<?> saveTransaction(TransactionRequestBody body) {
        if (null == body || null == body.timestamp() || null == body.amount()) {
            return ResponseEntity.badRequest().build();
        }

        var now = LocalDateTime.now();
        var timestamp = parseTimestampToDateTime(body.timestamp());

        if (!amountIsParsableToBigDecimal(body.amount()) || null == timestamp) {
            return ResponseEntity.unprocessableEntity().build();
        } else if (DateTimeUtils.happenedBeforeTheLast60seconds(timestamp, now)) {
            return ResponseEntity.noContent().build();
        } else if (DateTimeUtils.isFutureDate(timestamp, now)) {
            return ResponseEntity.unprocessableEntity().build();
        }

        publishTransaction(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private LocalDateTime parseTimestampToDateTime(String timestamp) {
        try {
            return DateTimeUtils.toLocalDateTime(timestamp);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static boolean amountIsParsableToBigDecimal(String amount) {
        try {
            new BigDecimal(amount);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Async
    private void publishTransaction(TransactionRequestBody body) {
        executorService.submit(() -> {
            var transaction = Transaction.of(body);
            transactions.add(transaction);
        });
    }

    public void deleteAllTransactions() {
        transactions.clear();
    }

    public StatisticResponseBody getLast60SecondsStatistics() {
        return StatisticResponseBody.of(statistic);
    }

    @Async
    @Scheduled(fixedRate = ONE_SECOND)
    void calculateStatistics() {
        cleanUpTransactions();

        var amounts = transactions.stream().map(Transaction::amount).toList();
        var sum = MathUtils.sum(amounts);
        var avg = MathUtils.average(amounts);
        var max = MathUtils.max(amounts);
        var min = MathUtils.min(amounts);
        var count = BigInteger.valueOf(amounts.size());

        statistic = new Statistic(sum, avg, max, min, count);
    }

    private void cleanUpTransactions() {
        var now = LocalDateTime.now();
        transactions = transactions.stream()
                .filter(transaction -> DateTimeUtils.happenedInTheLast60seconds(transaction.timestamp(), now))
                .collect(toCollection(ConcurrentLinkedQueue::new));
    }
}
