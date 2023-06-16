package com.antunesamaral.statisticsservice.utils;

import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static final int SIXTY_SECONDS = 60;
    public static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;


    public static LocalDateTime toLocalDateTime(String timestamp) {
        return LocalDateTime.parse(timestamp, formatter);
    }

    /**
     * Returns a {@link boolean} to indicate if event happened in the last 60 seconds.
     *
     * @param timestamp the timestamp that will be checked
     * @param now the now timestamp that will be compared with the timestamp param
     * @return {@code boolean}
     */
    public static boolean happenedInTheLast60seconds(@NonNull LocalDateTime timestamp, @NonNull LocalDateTime now) {
        var sixtySecondsBefore = now.minusSeconds(SIXTY_SECONDS);
        return timestamp.isAfter(sixtySecondsBefore);
    }

    /**
     * Returns a {@link boolean} to indicate if event happened before the last 60 seconds.
     *
     * @param timestamp the timestamp that will be checked
     * @param now the now timestamp that will be compared with the timestamp param
     * @return {@code boolean}
     */
    public static boolean happenedBeforeTheLast60seconds(@NonNull LocalDateTime timestamp, @NonNull LocalDateTime now) {
        var sixtySecondsBefore = now.minusSeconds(SIXTY_SECONDS);
        return timestamp.isBefore(sixtySecondsBefore);
    }

    /**
     * Returns a {@link boolean} to indicate if is a future date time.
     *
     * @param timestamp the timestamp that will be checked
     * @param now the now timestamp that will be compared with the timestamp param
     * @return {@code boolean}
     */
    public static boolean isFutureDate(@NonNull LocalDateTime timestamp, @NonNull LocalDateTime now) {
        return timestamp.isAfter(now);
    }
}
