package org.example;

public enum Cheese {
    MOZZARELLA, PARMESAN, RICOTTA, GOAT_CHEESE, BUFFALO;

    public double getPrice(PizzaSize size, boolean extra) {
        return switch (size) {
            case PERSONAL -> extra ? 0.30 : 0.75;
            case MEDIUM   -> extra ? 0.60 : 1.50;
            case LARGE    -> extra ? 0.90 : 2.25;
        };
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace("_", " ");
    }
}