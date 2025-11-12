package org.example;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private PizzaSize size;
    private CrustType crustType;
    private boolean stuffedCrust;
    private List<Topping> toppings = new ArrayList<>();
    private List<Sauce> sauces = new ArrayList<>();

    public Pizza(PizzaSize size, CrustType crustType) {
        this.size = size;
        this.crustType = crustType;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void addSauce(Sauce sauce) {
        sauces.add(sauce);
    }

    public double calculatePrice() {
        double total = size.getBasePrice();
        for (Topping t : toppings) total += t.getPrice(size);
        if (stuffedCrust) total += 2.00;
        return total;
    }

    public void setStuffedCrust(boolean stuffedCrust) {
        this.stuffedCrust = stuffedCrust;
    }

    @Override
    public String toString() {
        return String.format("%s %s Pizza with %s (Stuffed: %s) - $%.2f",
                size.getLabel(), crustType, toppings, stuffedCrust, calculatePrice());
    }
}
