package com.zihler.library.domain.values;

public class Amount {
    private double amount;

    public Amount(double amount) {
        this.amount = amount;
    }

    public static Amount of(double amount) {
        return new Amount(amount);
    }

    public Amount plus(Amount value) {
        this.amount = this.amount + value.asDouble();
        return Amount.of(this.amount);
    }

    public double asDouble() {
        return amount;
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
