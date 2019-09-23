package com.zihler.library;

import java.util.List;

class RentalRecord {
    private final Customer customer;
    private final List<Rental> rentals;

    RentalRecord(Customer customer, List<Rental> rentals) {
        this.customer = customer;
        this.rentals = rentals;
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
        return customer.getName();
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}
