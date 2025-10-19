package org.example;

import java.util.Random;

public class Cook implements Runnable {

    private final OrderTable orderTable;
    private int dishCounter = 0;
    private final Random random = new Random();

    public Cook(OrderTable orderTable) {
        this.orderTable = orderTable;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int cookingTime = random.nextInt(2000) + 1000;
                Thread.sleep(cookingTime);

                dishCounter++;
                Dish dish = new Dish("Страва №" + dishCounter + " від " + Thread.currentThread().getName());

                orderTable.placeDish(dish);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " був перерваний та завершує роботу.");
            Thread.currentThread().interrupt();
        }
    }
}