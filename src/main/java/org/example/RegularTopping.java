package org.example;

public enum RegularTopping
{
    ONIONS, MUSHROOMS, BELL_PEPPERS, OLIVES, TOMATOES,
    SPINACH, BASIL, JALAPENOS;

    @Override
    public String toString()
    {
        return name().toLowerCase().replace("_", " ");
    }
}