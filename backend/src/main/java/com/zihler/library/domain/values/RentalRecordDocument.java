package com.zihler.library.domain.values;

import java.util.List;
import java.util.stream.Collectors;

public class RentalRecordDocument {
    private final String customerName;
    private final int frequentRenterPoints;
    private final List<RentalDocument> rentals;
    private final double totalAmount;

    private RentalRecordDocument(String customerName, int frequentRenterPoints, List<RentalDocument> rentals, double totalAmount) {
        this.customerName = customerName;
        this.frequentRenterPoints = frequentRenterPoints;
        this.rentals = rentals;
        this.totalAmount = totalAmount;
    }

    static RentalRecordDocument from(RentalRecord rentalRecord) {
        return new RentalRecordDocument(
                rentalRecord.getCustomerName(),
                rentalRecord.getFrequentRenterPoints(),
                rentalRecord.getRentals().stream().map(Rental::asDocument).collect(Collectors.toList()),
                rentalRecord.getTotalAmount()
        );
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
