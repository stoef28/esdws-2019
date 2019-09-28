package com.zihler.domain;

public class DaysRented {
    private int daysRented;

    public DaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public static DaysRented from(int daysRented) {
        return new DaysRented(daysRented);
    }

    public double asDouble() {
        return daysRented;
    }


    public int asInt() {
        return daysRented;
    }

    @Override
    public String toString() {
        return String.valueOf(daysRented);
    }
}
