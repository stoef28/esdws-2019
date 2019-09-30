package com.zihler.domain;

public class DaysRented {
    private int daysRented;

    public DaysRented(int daysRented) {

        this.daysRented = daysRented;
    }

    public static DaysRented from(int daysRented) {
        return new DaysRented(daysRented);
    }

    public int toInt() {
        return daysRented;
    }
}
