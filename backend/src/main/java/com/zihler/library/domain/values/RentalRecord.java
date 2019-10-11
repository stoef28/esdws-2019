package com.zihler.library.domain.values;

import com.zihler.library.Customer;

import java.util.List;

public class RentalRecord {
    private Customer customer;
    private List<Rental> rentals;

    public RentalRecord(Customer customer, List<Rental> rentals) {
        this.customer = customer;
        this.rentals = rentals;
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        for (Rental rental : rentals) {
            totalAmount += rental.getAmount();
        }
        return totalAmount;
    }

    private Customer getCustomer() {
        return customer;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public String getCustomerName() {
        return getCustomer().getName();
    }

    public int getFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        for (Rental rental : rentals) {
            frequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }
}
