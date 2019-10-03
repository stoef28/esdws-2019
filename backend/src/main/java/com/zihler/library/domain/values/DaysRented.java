package com.zihler.library.domain.values;

public class DaysRented {
    private int daysRented;

    private DaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public static DaysRented from(String dayRentedAsString) {
        return new DaysRented(Integer.parseInt(dayRentedAsString));
    }

    public int asInt() {
        return daysRented;
    }

    @Override
    public String toString() {
        return String.valueOf(daysRented);
    }
}
