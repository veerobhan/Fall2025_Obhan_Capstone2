package org.example;

public class VeggiePizza extends Pizza {

    public VeggiePizza()
    {
        super(PizzaSize.PERSONAL, CrustType.REGULAR);

        addTopping(new Topping("Bell Peppers", ToppingType.REGULAR, false));
        addTopping(new Topping("Spinach", ToppingType.REGULAR, false));
        addTopping(new Topping("Olives", ToppingType.REGULAR, false));
        addTopping(new Topping("Onions", ToppingType.REGULAR, false));

        addSauce(Sauce.MARINARA);
        addTopping(new Topping("Mozzarella", ToppingType.CHEESE, false));
    }

    @Override
    public String toString()
    {
        return "Signature Veggie - " + super.toString();
    }
}
