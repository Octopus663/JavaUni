package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTableTest {

    @Test
    void testPlaceAndTakeDish() {
        System.out.println("--- Запуск тесту: testPlaceAndTakeDish ---");

        OrderTable table = new OrderTable(1);
        Dish testDish = new Dish("Тестова Страва");

        try {
            table.placeDish(testDish);
            Dish takenDish = table.takeDish();
            assertNotNull(takenDish, "Забрана страва не повинна бути null");
            assertEquals(testDish, takenDish, "Забрана страва має бути тією ж, що й покладена");
            System.out.println("Тест 'testPlaceAndTakeDish' пройдено успішно!");

        } catch (InterruptedException e) {
            fail("Тест був перерваний: " + e.getMessage());
        }
    }

}