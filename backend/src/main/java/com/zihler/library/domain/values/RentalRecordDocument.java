package com.zihler.library.domain.values;

import java.util.List;

public class RentalRecordDocument {
    private final String customerName;
    private final int frequentRenterPoints;
    private final List<RentalDocument> rentals;
    private final double totalAmount;

    public RentalRecordDocument(String customerName, int frequentRenterPoints, List<RentalDocument> rentals, double totalAmount) {
        this.customerName = customerName;
        this.frequentRenterPoints = frequentRenterPoints;
        this.rentals = rentals;
        this.totalAmount = totalAmount;
    }

    public RentalRecordDocument(RentalRecord rentalRecord) {
        this.customerName = rentalRecord.getCustomerName();
        this.frequentRenterPoints = rentalRecord.getFrequentRenterPoints();
        this.rentals = rentalRecord.getRentalsAsDocument();
        this.totalAmount = rentalRecord.getTotalAmount();
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }

    public List<RentalDocument> getRentals() {
        return rentals;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
