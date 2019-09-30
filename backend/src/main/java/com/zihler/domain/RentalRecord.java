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

    double getTotalAmount() {
        double totalAmount = 0;
        for (Rental rental : rentals) {
            totalAmount += rental.getAmount();
        }
        return totalAmount;
    }

    int getFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        for (Rental rental : rentals) {
            // add frequent renter points
            frequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

    String getCustomerName() {
        return customer.getCustomerName().toString();
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public RentalsDocument asDocument() {
        return new RentalsDocument(this);
    }
}
