package org.example;

public class Drink
{
    private String size;
    private String flavor;
    private double price;

    public Drink(String size, String flavor, double price)
    {
        this.size = size;
        this.flavor = flavor;
        this.price = price;
    }

    public double getPrice()
    {
        return price;
    }

    @Override
    public String toString()
    {
        return size + " " + flavor + " Drink - $" + price;
    }

    public String getSize()
    {
        return size;
    }

    public String getFlavor()
    {
        return flavor;
    }
}
