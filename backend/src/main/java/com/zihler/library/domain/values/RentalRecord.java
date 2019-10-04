package com.zihler.library.domain.values;

import java.util.List;

public class RentalRecord {
    private final Customer customer;
    private final List<Rental> rentals;

    private RentalRecord(Customer customer, List<Rental> rentals) {
        this.customer = customer;
        this.rentals = rentals;
    }

    public static RentalRecord from(Customer customer, List<Rental> rentals) {
        return new RentalRecord(customer, rentals);
    }

    public CustomerName customerName() {
        return customer.name();
    }

    public List<Rental> rentals() {
        return rentals;
    }

    public Amount totalAmount() {
        Amount totalAmount = Amount.of(0);
        for (Rental rental : rentals) {
            totalAmount.plus(rental.amount());
        }
        return totalAmount;
    }

    public FrequentRenterPoints frequentRenterPoints() {
        FrequentRenterPoints frequentRenterPoints = FrequentRenterPoints.of(0);

        for (Rental rental : rentals) {
            frequentRenterPoints.plus(rental.frequentRenterPoints());
        }

        return frequentRenterPoints;
    }
}
