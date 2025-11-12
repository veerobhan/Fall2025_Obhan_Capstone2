package org.example;

public class Topping {
    private String name;
    private ToppingType type;
    private boolean extra;

    public Topping(String name, ToppingType type, boolean extra) {
        this.name = name;
        this.type = type;
        this.extra = extra;
    }

    public double getPrice(PizzaSize size) {
        return switch (type) {
            case MEAT -> Meat.valueOf(name.toUpperCase()).getPrice(size, extra);
            case CHEESE -> Cheese.valueOf(name.toUpperCase().replace(" ", "_")).getPrice(size, extra);
            case REGULAR -> 0.0;
        };
    }

    @Override
    public String toString() {
        return name + (extra ? " (extra)" : "");
    }
}
