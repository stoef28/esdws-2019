package com.zihler.domain;

public class FrequentRenterPoints {
    private int frequentRenterPoints;

    private FrequentRenterPoints(int frequentRenterPoints) {
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public static FrequentRenterPoints of(int frequentRenterPoints) {
        return new FrequentRenterPoints(frequentRenterPoints);
    }

    public static FrequentRenterPoints zero() {
        return of(0);
    }

    public static FrequentRenterPoints one() {
        return of(1);
    }

    public int get() {
        return frequentRenterPoints;
    }

    public void add(FrequentRenterPoints frequentRenterPoints) {
        this.frequentRenterPoints += frequentRenterPoints.get();
    }

    @Override
    public String toString() {
        return String.valueOf(frequentRenterPoints);
    }
}
