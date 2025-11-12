package org.example;


public enum Meat {
    PEPPERONI, SAUSAGE, HAM, BACON, CHICKEN, MEATBALL;

    public double getPrice(PizzaSize size, boolean extra) {
        return switch (size) {
            case PERSONAL -> extra ? 0.50 : 1.00;
            case MEDIUM   -> extra ? 1.00 : 2.00;
            case LARGE    -> extra ? 1.50 : 3.00;
        };
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}