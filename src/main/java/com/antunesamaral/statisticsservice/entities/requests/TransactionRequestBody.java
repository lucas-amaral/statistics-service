package com.antunesamaral.statisticsservice.entities.requests;

import java.util.Objects;

public record TransactionRequestBody(String amount, String timestamp) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionRequestBody that = (TransactionRequestBody) o;
        return Objects.equals(amount, that.amount) && Objects.equals(timestamp, that.timestamp);
    }

}
