package org.example;

import java.util.stream.Collector;

public class StatisticsCollector {
    public static Collector<Car, Statistics, Statistics> toStatistics() {
        return Collector.of(
                Statistics::new,
                Statistics::accept,
                Statistics::merge,
                Collector.Characteristics.IDENTITY_FINISH
        );
    }
}
