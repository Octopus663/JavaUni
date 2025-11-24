package org.example;

import org.example.Car;
import org.example.Statistics;
import org.example.StatisticsCollector;
import org.example.CarGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        final int LIMIT = 500;
        final int SKIP_N_MATCHING = 5;
        final String SKIP_CONDITION_MAKE = "BMW";

        System.out.println("=== 1. Generating & Gathering Stream ===");

        Gatherer<Car, ?, Car> skipFirstNByMake = Gatherer.ofSequential(

                () -> new int[]{0},

                (state, element, downstream) -> {
                    if (element.make().equals(SKIP_CONDITION_MAKE) && state[0] < SKIP_N_MATCHING) {
                        state[0]++;
                        return true;
                    }
                    return downstream.push(element);
                }
        );

        List<Car> carList = Stream.generate(new CarGenerator())
                .gather(skipFirstNByMake)
                .limit(LIMIT)
                .toList();

        System.out.println("Generated list size: " + carList.size());

        Map<String, List<Car>> groupedByClass = carList.stream()
                .filter(c -> c.getMonthsSinceProduction() > 12 && c.getMonthsSinceProduction() < 60)
                .collect(Collectors.groupingBy(Car::carClass));

        System.out.println("\n=== 2. Grouped by Class (Example keys) ===");
        groupedByClass.keySet().forEach(System.out::println);

        Statistics stats = carList.stream()
                .collect(StatisticsCollector.toStatistics());

        System.out.println("\n=== 3. Statistics ===");
        System.out.println(stats);

        List<Double> sortedPrices = stats.getPrices().stream().sorted().toList();
        double q1 = getQuantile(sortedPrices, 0.25);
        double q3 = getQuantile(sortedPrices, 0.75);
        double iqr = q3 - q1;
        double lowerBound = q1 - 1.5 * iqr;
        double upperBound = q3 + 1.5 * iqr;

        System.out.printf("\nIQR Analysis: Q1=%.2f, Q3=%.2f, IQR=%.2f\n", q1, q3, iqr);
        System.out.printf("Bounds: [%.2f; %.2f]\n", lowerBound, upperBound);

        Map<Boolean, Long> outlierCounts = carList.stream()
                .collect(Collectors.partitioningBy(
                        c -> c.price() < lowerBound || c.price() > upperBound,
                        Collectors.counting()
                ));

        System.out.println("\n=== 4. Result Structure ===");

        System.out.printf("{\"data\": %d, \"outliers\": %d}%n",
                outlierCounts.get(false), outlierCounts.get(true));
    }

    private static double getQuantile(List<Double> sortedData, double quantile) {
        int index = (int) Math.ceil(quantile * sortedData.size()) - 1;
        return sortedData.get(Math.max(0, index));
    }
}