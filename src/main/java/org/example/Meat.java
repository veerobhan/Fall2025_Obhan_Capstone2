package org.example;


public enum Meat
{
    PEPPERONI, SAUSAGE, CHICKEN, MEATBALL, PROSCIUTTO;

    public double getPrice(PizzaSize size, boolean extra) {
        return switch (size) {
            case PERSONAL -> extra ? 1.00 : 3.00;
            case MEDIUM   -> extra ? 2.00 : 4.00;
            case LARGE    -> extra ? 3.00 : 5.00;
        };
    }

    @Override
    public String toString()
    {
        return name().toLowerCase();
    }
}