package org.example;

public enum Cheese
{
    MOZZARELLA, PARMESAN, RICOTTA;

    public double getPrice(PizzaSize size, boolean extra)
    {
        return switch (size)
        {
            case PERSONAL -> extra ? 0.00 : 1.00;
            case MEDIUM   -> extra ? 0.75 : 2.00;
            case LARGE    -> extra ? 1.50 : 3.00;
        };
    }

    @Override
    public String toString()
    {
        return name().toLowerCase().replace("_", " ");
    }
}