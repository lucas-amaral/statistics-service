package com.antunesamaral.statisticsservice.utils;

import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;

public class MathUtils {

    /**
     * Returns a {@link BigDecimal} that specifying the total sum of values for a list.
     *
     * @param values to sum
     * @return {@code BigDecimal}
     */
    public static BigDecimal sum(@NonNull List<BigDecimal> values) {
        return values.stream()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, HALF_UP);
    }

    /**
     * Returns a {@link BigDecimal} that specifying the total average of values for a list.
     *
     * @param values the numbers to calculate the average
     * @return {@code BigDecimal}
     */
    public static BigDecimal average(@NonNull List<BigDecimal> values) {
        var avg = values.stream()
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0d);

        return toBigDecimal(avg);
    }

    /**
     * Returns a {@link BigDecimal} with the highest value in the list.
     *
     * @param values the numbers to find the highest value
     * @return {@code BigDecimal}
     */
    public static BigDecimal max(@NonNull List<BigDecimal> values) {
        var max = values.stream()
                .mapToDouble(BigDecimal::doubleValue)
                .max()
                .orElse(0d);

        return toBigDecimal(max);
    }

    /**
     * Returns a {@link BigDecimal} with the lowest value in the list.
     *
     * @param values the numbers to find the lowest value
     * @return {@code BigDecimal}
     */
    public static BigDecimal min(@NonNull List<BigDecimal> values) {
        var min = values.stream()
                .mapToDouble(BigDecimal::doubleValue)
                .min()
                .orElse(0d);

        return toBigDecimal(min);
    }

    private static BigDecimal toBigDecimal(Double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, HALF_UP);
    }
}
