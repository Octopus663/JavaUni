package org.example;

import org.example.Car;
import java.time.LocalDate;
import java.util.Random;
import java.util.function.Supplier;

public class CarGenerator implements Supplier<Car> {
    private static final Random RANDOM = new Random();
    private static final String[] MAKES = {"Toyota", "BMW", "Audi", "Ford", "Mercedes"};
    private static final String[] CLASSES = {"Economy", "Business", "Premium", "SUV"};

    @Override
    public Car get() {
        String make = MAKES[RANDOM.nextInt(MAKES.length)];
        String model = "Model-" + RANDOM.nextInt(100);
        String carClass = CLASSES[RANDOM.nextInt(CLASSES.length)];
        // Random date within last 10 years
        LocalDate date = LocalDate.now().minusMonths(RANDOM.nextInt(120));
        // Price between 10,000 and 100,000
        double price = 10_000 + (90_000 * RANDOM.nextDouble());

        return new Car(make, model, date, carClass, price);
    }
}