package org.example;

public class GarlicKnots
{
    private int quantity;
    private double pricePerUnit;

    public GarlicKnots(int quantity, double pricePerUnit)
    {
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public double getPrice()
    {
        return quantity * pricePerUnit;
    }

    @Override
    public String toString()
    {
        return quantity + " Garlic Knots - $" + getPrice();
    }

    public String getCount()
    {
        return Integer.toString(quantity);
    }
}