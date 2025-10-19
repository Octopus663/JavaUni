package org.example;

import java.util.NoSuchElementException;
import java.util.Random;


public class Waiter implements Runnable {

    private final OrderTable orderTable;
    private final Random random = new Random();

    public Waiter(OrderTable orderTable) {
        this.orderTable = orderTable;
    }

    @Override
    public void run() {
        try {
            while (true) {

                Dish dish = orderTable.takeDish();

                int deliveryTime = random.nextInt(1500) + 500;
                Thread.sleep(deliveryTime);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " був перерваний та завершує роботу.");
            Thread.currentThread().interrupt();
        }
    }
}
