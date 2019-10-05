package com.zihler.library.domain.values;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class RentalRecordDocument {
    private final CustomerName customerName;
    private final FrequentRenterPoints frequentRenterPoints;
    private final Amount totalAmount;
    private final List<RentalDocument> rentals;

    private RentalRecordDocument(CustomerName customerName, FrequentRenterPoints frequentRenterPoints, Amount totalAmount, List<RentalDocument> rentals) {
        this.customerName = customerName;
        this.frequentRenterPoints = frequentRenterPoints;
        this.totalAmount = totalAmount;
        this.rentals = rentals;
    }

    public static RentalRecordDocument from(RentalRecord rentalRecord) {
        return new RentalRecordDocument(
                rentalRecord.customerName(),
                rentalRecord.frequentRenterPoints(),
                rentalRecord.totalAmount(),
                rentalRecord.rentals().stream().map(Rental::asDocument).collect(toList())
        );
    }

    public CustomerName customerName() {
        return customerName;
    }

    public FrequentRenterPoints frequentRenterPoints() {
        return frequentRenterPoints;
    }

    public Amount totalAmount() {
        return totalAmount;
    }

    public List<RentalDocument> rentals() {
        return rentals;
    }
}
