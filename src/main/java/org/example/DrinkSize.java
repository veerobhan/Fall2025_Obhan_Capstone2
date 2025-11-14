package org.example;


public enum DrinkSize
{
    SMALL("Small", 2.00),
    MEDIUM("Medium", 2.50),
    LARGE("Large", 3.00);

    private final double price;

    DrinkSize(String label, double price)
    {
        this.price = price;
    }

    public double getPrice()
    {
        return price;
    }

    @Override
    public String toString()
    {
        return name().toLowerCase();
    }
}