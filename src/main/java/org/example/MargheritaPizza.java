package org.example;

public class MargheritaPizza extends Pizza {

    public MargheritaPizza() {
        super(PizzaSize.PERSONAL, CrustType.REGULAR);

        addTopping(new Topping("Mozzarella", ToppingType.CHEESE, false));

        addTopping(new Topping("Tomatoes", ToppingType.REGULAR, false));
        addTopping(new Topping("Basil", ToppingType.REGULAR, false));

        addSauce(Sauce.MARINARA);
        addSauce(Sauce.OLIVE_OIL);
    }

    @Override
    public String toString()
    {
        return "Signature Margherita - " + super.toString();
    }
}
