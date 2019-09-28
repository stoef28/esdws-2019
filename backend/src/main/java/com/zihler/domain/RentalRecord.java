package com.zihler.domain;

import com.zihler.domain.entities.Customer;

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

    Amount totalAmount() {
        Amount totalAmount = Amount.of(0);
        for (Rental rental : rentals) {
            totalAmount.add(rental.amount());
        }
        return totalAmount;
    }

    FrequentRenterPoints frequentRenterPoints() {
        FrequentRenterPoints frequentRenterPoints = FrequentRenterPoints.zero();
        for (Rental rental : rentals) {
            // add frequent renter points
            frequentRenterPoints.add(rental.frequentRenterPoints());
        }
        return frequentRenterPoints;
    }

    CustomerName customerName() {
        return customer.customerName();
    }

    public List<Rental> rentals() {
        return rentals;
    }

    public RentalsDocument asDocument() {
        return RentalsDocument.of(this);
    }
}
