package com.antunesamaral.statisticsservice.entities;

import com.antunesamaral.statisticsservice.entities.requests.TransactionRequestBody;
import com.antunesamaral.statisticsservice.utils.DateTimeUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record Transaction(BigDecimal amount, LocalDateTime timestamp) {

    public static Transaction of(TransactionRequestBody body) {
        return new Transaction(new BigDecimal(body.amount()), DateTimeUtils.toLocalDateTime(body.timestamp()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(amount, that.amount) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public String toString() {
        return "Transaction{" + "amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
