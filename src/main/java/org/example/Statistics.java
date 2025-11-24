package org.example;

import org.example.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

/**
 * Container for statistical data.
 */
public class Statistics {
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private double sum = 0;
    private double sumSq = 0;
    private long count = 0;
    // Ми зберігаємо список цін, щоб потім порахувати квартилі (IQR)
    private final List<Double> prices = new ArrayList<>();

    public void accept(Car car) {
        double price = car.price();
        if (price < min) min = price;
        if (price > max) max = price;
        sum += price;
        sumSq += price * price;
        count++;
        prices.add(price);
    }

    public Statistics merge(Statistics other) {
        if (other.min < this.min) this.min = other.min;
        if (other.max > this.max) this.max = other.max;
        this.sum += other.sum;
        this.sumSq += other.sumSq;
        this.count += other.count;
        this.prices.addAll(other.prices);
        return this;
    }

    public double getMean() {
        return count == 0 ? 0 : sum / count;
    }

    public double getStandardDeviation() {
        if (count <= 1) return 0;
        double variance = (sumSq - (sum * sum) / count) / (count - 1); // Sample std dev
        return Math.sqrt(variance);
    }

    public List<Double> getPrices() {
        return prices;
    }

    @Override
    public String toString() {
        return String.format(
                "Min: %.2f, Max: %.2f, Mean: %.2f, StdDev: %.2f",
                min, max, getMean(), getStandardDeviation()
        );
    }
}
