package com.antunesamaral.statisticsservice.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DateTimeUtilsTest {

    @Test
    void happenedInTheLast60seconds_shouldReturnTrueForDateTimeInTheLast1Minute() {
        var timestamp = LocalDateTime.of(2023, 1, 3, 14, 3, 4);
        var now = LocalDateTime.of(2023, 1, 3, 14, 4, 3);
        assertThat(DateTimeUtils.happenedInTheLast60seconds(timestamp, now)).isTrue();
    }

    @Test
    void happenedInTheLast60seconds_shouldReturnFalseForDateTimeBeforeTheLast1Minute() {
        var timestamp = LocalDateTime.of(2023, 1, 3, 14, 3, 2);
        var now = LocalDateTime.of(2023, 1, 3, 14, 4, 3);
        assertThat(DateTimeUtils.happenedInTheLast60seconds(timestamp, now)).isFalse();
    }

    @Test
    void happenedBeforeTheLast60seconds_shouldReturnTrueForDateTimeBeforeTheLast1Minute() {
        var timestamp = LocalDateTime.of(2023, 1, 3, 14, 3, 0);
        var now = LocalDateTime.of(2023, 1, 3, 14, 4, 3);
        assertThat(DateTimeUtils.happenedBeforeTheLast60seconds(timestamp, now)).isTrue();
    }

    @Test
    void happenedBeforeTheLast60seconds_shouldReturnFalseForDateTimeInTheLast1Minute() {
        var timestamp = LocalDateTime.of(2023, 1, 3, 14, 3, 5);
        var now = LocalDateTime.of(2023, 1, 3, 14, 4, 3);
        assertThat(DateTimeUtils.happenedBeforeTheLast60seconds(timestamp, now)).isFalse();
    }

    @Test
    void isFutureDate_withFutureDate() {
        var timestamp = LocalDateTime.of(2023, 1, 3, 14, 4, 4);
        var now = LocalDateTime.of(2023, 1, 3, 14, 4, 3);
        assertThat(DateTimeUtils.isFutureDate(timestamp, now)).isTrue();
    }

    @Test
    void isFutureDate_withNotFutureDate() {
        var timestamp = LocalDateTime.of(2023, 1, 2, 19, 34, 47);
        var now = LocalDateTime.of(2023, 1, 3, 14, 4, 3);
        assertThat(DateTimeUtils.isFutureDate(timestamp, now)).isFalse();
    }
}