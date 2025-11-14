package org.example;

public enum DrinkType
{
    COKE("Coke"),
    SPRITE("Sprite"),
    Fanta("Fanta"),
    LEMONADE("Lemonade"),
    ICED_TEA("Iced Tea"),
    WATER("Water"),
    NONE("No Drink");

    private final String label;

    DrinkType(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
}
