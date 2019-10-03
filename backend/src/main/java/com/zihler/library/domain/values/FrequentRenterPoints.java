package com.zihler.library.domain.values;

public class FrequentRenterPoints {
    private int frequentRenterPoints;

    public FrequentRenterPoints(int frequentRenterPoints) {
        this.frequentRenterPoints = frequentRenterPoints;
    }

    public static FrequentRenterPoints of(int frequentRenterPoints) {
        return new FrequentRenterPoints(frequentRenterPoints);
    }

    public int asInt() {
        return frequentRenterPoints;
    }

    public FrequentRenterPoints plus(FrequentRenterPoints value) {
        frequentRenterPoints = this.frequentRenterPoints + value.asInt();
        return FrequentRenterPoints.of(frequentRenterPoints);
    }

    @Override
    public String toString() {
        return String.valueOf(frequentRenterPoints);
    }
}
