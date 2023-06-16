package com.antunesamaral.statisticsservice.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {


    @Test
    void sum_shouldAddZeroIfThereIsJustOneDigitAfterDot() {
        var list = List.of(
                new BigDecimal("1.2"),
                new BigDecimal("2"),
                new BigDecimal("4.15"),
                new BigDecimal("2.45")
        );

        assertThat(MathUtils.sum(list))
                .isEqualTo(new BigDecimal("9.80"));
    }

    @Test
    void sum_shouldRoundUpValue() {
        var list = List.of(
                new BigDecimal("1.2"),
                new BigDecimal("2.005"),
                new BigDecimal("4.14"),
                new BigDecimal("3.00")
        );

        assertThat(MathUtils.sum(list))
                .isEqualTo(new BigDecimal("10.35"));
    }

    @Test
    void sum_withOnlyOneValue() {
        var list = List.of(
                new BigDecimal("2.005")
        );

        assertThat(MathUtils.sum(list))
                .isEqualTo(new BigDecimal("2.01"));
    }

    @Test
    void sum_shouldReturnZeroIfListIsEmpty() {
        assertThat(MathUtils.sum(Collections.emptyList()))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void sum_shouldThrowExceptionIfListIsEmpty() {
        assertThrows(NullPointerException.class, () -> MathUtils.sum(null));
    }

    @Test
    void average_shouldAddZeroIfThereIsJustOneDigitAfterDot() {
        var list = List.of(
                new BigDecimal("1.2"),
                new BigDecimal("1.2")
        );

        assertThat(MathUtils.average(list))
                .isEqualTo(new BigDecimal("1.20"));
    }

    @Test
    void average_shouldRoundUpValue() {
        var list = List.of(
                new BigDecimal("1.2"),
                new BigDecimal("2.005"),
                new BigDecimal("4.14"),
                new BigDecimal("3.00")
        );

        assertThat(MathUtils.average(list))
                .isEqualTo(new BigDecimal("2.59"));
    }

    @Test
    void average_withOnlyOneValue() {
        var list = List.of(
                new BigDecimal("2.005")
        );

        assertThat(MathUtils.average(list))
                .isEqualTo(new BigDecimal("2.01"));
    }

    @Test
    void average_shouldReturnZeroIfListIsEmpty() {
        assertThat(MathUtils.average(Collections.emptyList()))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void average_shouldThrowExceptionIfListIsEmpty() {
        assertThrows(NullPointerException.class, () -> MathUtils.average(null));
    }

    @Test
    void max_shouldAddZeroIfThereIsJustOneDigitAfterDot() {
        var list = List.of(
                new BigDecimal("1.2"),
                new BigDecimal("2"),
                new BigDecimal("4.1"),
                new BigDecimal("2.45")
        );

        assertThat(MathUtils.max(list))
                .isEqualTo(new BigDecimal("4.10"));
    }

    @Test
    void max_shouldRoundUpValue() {
        var list = List.of(
                new BigDecimal("1.2"),
                new BigDecimal("2.005"),
                new BigDecimal("4.14513224"),
                new BigDecimal("3.00")
        );

        assertThat(MathUtils.max(list))
                .isEqualTo(new BigDecimal("4.15"));
    }

    @Test
    void max_withOnlyOneValue() {
        var list = List.of(
                new BigDecimal("2.005000000000")
        );

        assertThat(MathUtils.max(list))
                .isEqualTo(new BigDecimal("2.01"));
    }

    @Test
    void max_shouldReturnZeroIfListIsEmpty() {
        assertThat(MathUtils.max(Collections.emptyList()))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void max_shouldThrowExceptionIfListIsEmpty() {
        assertThrows(NullPointerException.class, () -> MathUtils.max(null));
    }

    @Test
    void min_shouldAddZeroIfThereIsNoDigitAfterDot() {
        var list = List.of(
                new BigDecimal("1"),
                new BigDecimal("2"),
                new BigDecimal("4.15"),
                new BigDecimal("2.45")
        );

        assertThat(MathUtils.min(list))
                .isEqualTo(new BigDecimal("1.00"));
    }

    @Test
    void min_shouldRoundUpValue() {
        var list = List.of(
                new BigDecimal("1.2"),
                new BigDecimal("2.005"),
                new BigDecimal("4.14"),
                new BigDecimal("3.00")
        );

        assertThat(MathUtils.min(list))
                .isEqualTo(new BigDecimal("1.20"));
    }

    @Test
    void min_withOnlyOneValue() {
        var list = List.of(
                new BigDecimal("2.005")
        );

        assertThat(MathUtils.min(list))
                .isEqualTo(new BigDecimal("2.01"));
    }

    @Test
    void min_shouldReturnZeroIfListIsEmpty() {
        assertThat(MathUtils.min(Collections.emptyList()))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void min_shouldThrowExceptionIfListIsEmpty() {
        assertThrows(NullPointerException.class, () -> MathUtils.min(null));
    }
}