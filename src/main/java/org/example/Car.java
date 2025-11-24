package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Car(
        String make,
        String model,
        LocalDate manufactureDate,
        String carClass,
        double price
) {
    /**
     * Calculates full months since production.
     */
    public long getMonthsSinceProduction() {
        return ChronoUnit.MONTHS.between(manufactureDate, LocalDate.now());
    }
}