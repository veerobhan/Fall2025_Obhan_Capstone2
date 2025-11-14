package org.example;

public enum PizzaSize
{
    PERSONAL("8\"", 12.00),
    MEDIUM("12\"", 15.00),
    LARGE("16\"", 18.00);

    private final String label;
    private final double basePrice;

    PizzaSize(String label, double basePrice)
    {
        this.label = label;
        this.basePrice = basePrice;
    }

    public double getBasePrice()
    {
        return basePrice;
    }

    public String getLabel()
    {
        return label;
    }

    @Override
    public String toString()
    {
        return label;
    }
}