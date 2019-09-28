package com.zihler.domain;

public class Amount {
    private double amount;

    private Amount(double amount) {
        this.amount = amount;
    }

    public static Amount of(double amount) {
        return new Amount(amount);
    }

    public static Amount zero() {
        return of(0);
    }

    public void add(Amount amount) {
        this.amount += amount.get();
    }

    public double get() {
        return amount;
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
