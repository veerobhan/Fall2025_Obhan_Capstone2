package org.example;

public enum CrustType {
    THIN,
    REGULAR,
    THICK,
    CAULIFLOWER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}