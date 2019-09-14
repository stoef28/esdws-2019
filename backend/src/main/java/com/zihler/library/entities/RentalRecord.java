package com.zihler.library.entities;

import java.util.List;

public class RentalRecord {
    final Customer customer;
    final List<Rental> rentals;

    public RentalRecord(Customer customer, List<Rental> rentals) {
        this.customer = customer;
        this.rentals = rentals;
    }

    public String getCustomerName() {
        return customer.getName();
    }

    public int getFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        for (Rental rental : this.rentals) {
            frequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        for (Rental rental : rentals) {
            totalAmount += rental.getAmount();
        }
        return totalAmount;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}
