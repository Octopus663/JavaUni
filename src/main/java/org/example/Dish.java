package org.example;

public class Dish {
    private final String name;

    public Dish(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Dish[" + name + ']';
    }
}
