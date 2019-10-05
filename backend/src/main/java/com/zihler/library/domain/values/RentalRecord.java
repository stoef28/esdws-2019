package com.zihler.library.domain.values;

import com.zihler.library.domain.entities.Customer;

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

    String getCustomerName() {
        return customer.getName();
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
            frequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

    List<Rental> getRentals() {
        return rentals;
    }

    public RentalRecordDocument asDocument() {
        return RentalRecordDocument.from(this);
    }
}
