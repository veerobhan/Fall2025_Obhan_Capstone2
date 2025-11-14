package org.example;

import java.util.ArrayList;
import java.util.List;

public class Pizza
{
    private PizzaSize size;
    private CrustType crustType;
    private List<Topping> toppings = new ArrayList<>();
    private List<Sauce> sauces = new ArrayList<>();

    public Pizza(PizzaSize size, CrustType crustType)
    {
        this.size = size;
        this.crustType = crustType;
    }


    public void addTopping(Topping topping)
    {
        toppings.add(topping);
    }

    public void addSauce(Sauce sauce)
    {
        sauces.add(sauce);
    }

    public double calculatePrice()
    {
        double total = size.getBasePrice();
        for (Topping t : toppings) total += t.getPrice(size);
        return total;
    }

    @Override
    public String toString()
    {
        return String.format("%s %s Pizza with %s - $%.2f",
                size.getLabel(), crustType, toppings, calculatePrice());
    }

    public String getSize()
    {
        return size.getLabel();
    }

    public String getCrust()
    {
        return crustType.toString();
    }

    public Topping[] getToppings()
    {
        return toppings.toArray(new Topping[0]);
    }

    public Sauce[] getSauces()
    {
        return sauces.toArray(new Sauce[0]);
    }
}
