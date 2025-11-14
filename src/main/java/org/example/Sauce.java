package org.example;

public enum Sauce
{
    MARINARA, ALFREDO, PESTO, OLIVE_OIL;

    @Override
    public String toString()
    {
        return name().toLowerCase().replace("_", " ");
    }
}