package com.antunesamaral.statisticsservice.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public record Statistic(BigDecimal sum, BigDecimal avg, BigDecimal max, BigDecimal min, BigInteger count) {

    public static Statistic empty() {
        return new Statistic(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigInteger.ZERO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return Objects.equals(sum, statistic.sum) && Objects.equals(avg, statistic.avg) && Objects.equals(max, statistic.max) && Objects.equals(min, statistic.min) && Objects.equals(count, statistic.count);
    }

    @Override
    public String toString() {
        return "Statistic{" + "sum=" + sum +
                ", avg=" + avg +
                ", max=" + max +
                ", min=" + min +
                ", count=" + count +
                '}';
    }
}
