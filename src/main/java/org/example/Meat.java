package org.example;


public enum Meat
{
    PEPPERONI, SAUSAGE, CHICKEN, MEATBALL;

    public double getPrice(PizzaSize size, boolean extra) {
        return switch (size) {
            case PERSONAL -> extra ? 0.00 : 2.00;
            case MEDIUM   -> extra ? 0.50 : 3.50;
            case LARGE    -> extra ? 1.00 : 5.00;
        };
    }

    @Override
    public String toString()
    {
        return name().toLowerCase();
    }
}