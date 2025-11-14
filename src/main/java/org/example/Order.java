package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order
{
    private List<Pizza> pizzas = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<GarlicKnots> garlicKnots = new ArrayList<>();
    private LocalDateTime timestamp = LocalDateTime.now();

    public void addPizza(Pizza pizza)
    {
        pizzas.add(pizza);
    }

    public void addDrink(Drink drink)
    {
        drinks.add(drink);
    }

    public void addGarlicKnots(GarlicKnots knots)
    {
        garlicKnots.add(knots);
    }

    public double calculateTotal()
    {
        double total = 0;
        for (Pizza p : pizzas) total += p.calculatePrice();
        for (Drink d : drinks) total += d.getPrice();
        for (GarlicKnots g : garlicKnots) total += g.getPrice();
        return total;
    }

    public void displayOrderDetails()
    {
        System.out.println("\n--- Order Details ---");
        for (Pizza p : pizzas) System.out.println(p);
        for (Drink d : drinks) System.out.println(d);
        for (GarlicKnots g : garlicKnots) System.out.println(g);
        System.out.printf("Total: $%.2f%n", calculateTotal());
    }

    public void saveReceipt()
    {
        new ReceiptWriter().saveToFile(this);
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public List<Pizza> getPizzas() { return pizzas; }
    public List<Drink> getDrinks() { return drinks; }
    public List<GarlicKnots> getGarlicKnots() { return garlicKnots; }
}