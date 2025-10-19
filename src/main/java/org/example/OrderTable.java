package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class OrderTable {
    private final Queue<Dish> dishes = new LinkedList<>();
    private final int maxSize;

    public OrderTable(int maxSize) {
        this.maxSize = maxSize;
        System.out.println("Стіл для замовлень відкрито (розмір: " + maxSize + ").");
    }

    public synchronized void placeDish(Dish dish) throws InterruptedException {

        while (dishes.size() == maxSize) {
            System.out.println(
                    "--- СТІЛ ПОВНИЙ! Кухар " + Thread.currentThread().getName() +
                            " чекає, поки звільниться місце..."
            );
            wait();
        }
        dishes.add(dish);
        System.out.println(
                "-> Кухар " + Thread.currentThread().getName() + " поставив: " + dish.getName() +
                        ". (На столі: " + dishes.size() + ")"
        );
        notifyAll();
    }
    public synchronized Dish takeDish() throws InterruptedException {

        while (dishes.isEmpty()) {
            System.out.println(
                    "--- СТІЛ ПОРОЖНІЙ! Офіціант " + Thread.currentThread().getName() +
                            " чекає на нову страву..."
            );
            wait();
        }

        Dish dish = dishes.poll();
        System.out.println(
                "<- Офіціант " + Thread.currentThread().getName() + " забрав: " + dish.getName() +
                        ". (Залишилось: " + dishes.size() + ")"
        );
        notifyAll();

        return dish;
    }
}
