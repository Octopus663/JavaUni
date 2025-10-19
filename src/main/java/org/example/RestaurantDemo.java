package org.example;

public class RestaurantDemo {

    public static void main(String[] args) {

        OrderTable orderTable = new OrderTable(3);

        System.out.println("--- ДЕМОНСТРАЦІЯ НЕКОРЕКТНОЇ РОБОТИ (БЕЗ СИНХРОНІЗАЦІЇ) ---");
        System.out.println("Очікувані помилки: 'NoSuchElementException' (офіціант бере з порожнього столу) або 'ПРОБЛЕМА: Стіл повний!'");

        Cook cook1 = new Cook(orderTable);
        Cook cook2 = new Cook(orderTable);

        Waiter waiter1 = new Waiter(orderTable);
        Waiter waiter2 = new Waiter(orderTable);
        Waiter waiter3 = new Waiter(orderTable);

        new Thread(cook1, "Кухар-1").start();
        new Thread(cook2, "Кухар-2").start();

        new Thread(waiter1, "Офіціант-1").start();
        new Thread(waiter2, "Офіціант-2").start();
        new Thread(waiter3, "Офіціант-3").start();

        System.out.println("--- Ресторан запрацював! (Потоки запущено) ---");
    }
}
