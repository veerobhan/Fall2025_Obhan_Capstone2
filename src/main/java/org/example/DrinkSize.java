package org.example;


public enum DrinkSize {
    SMALL(2.00),
    MEDIUM(2.50),
    LARGE(3.00);

    private final double price;

    DrinkSize(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}