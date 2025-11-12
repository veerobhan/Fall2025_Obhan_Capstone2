package org.example;

public enum Sauce {
    MARINARA, ALFREDO, PESTO, BBQ, BUFFALO, OLIVE_OIL;

    @Override
    public String toString() {
        return name().toLowerCase().replace("_", " ");
    }
}