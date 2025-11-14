package org.example;

public enum CrustType {
    THIN,
    REGULAR,
    DEEP_DISH;

    @Override
    public String toString()
    {
        return name().toLowerCase();
    }
}