package com.antunesamaral.statisticsservice.entities.responses;

import com.antunesamaral.statisticsservice.entities.Statistic;

import java.math.BigInteger;
import java.util.Objects;

public record StatisticResponseBody(String sum, String avg, String max, String min, BigInteger count) {
    public static StatisticResponseBody of(Statistic statistic) {
        return new StatisticResponseBody(
                statistic.sum().toString(),
                statistic.avg().toString(),
                statistic.max().toString(),
                statistic.min().toString(),
                statistic.count());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticResponseBody that = (StatisticResponseBody) o;
        return Objects.equals(sum, that.sum) && Objects.equals(avg, that.avg) && Objects.equals(max, that.max) && Objects.equals(min, that.min) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum, avg, max, min, count);
    }

    @Override
    public String toString() {
        return "StatisticResponseBody{" + "sum='" + sum + '\'' +
                ", avg='" + avg + '\'' +
                ", max='" + max + '\'' +
                ", min='" + min + '\'' +
                ", count=" + count +
                '}';
    }
}
