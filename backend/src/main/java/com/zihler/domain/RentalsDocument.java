package com.zihler.domain;

import java.util.List;
import java.util.stream.Collectors;

public class RentalsDocument {
    private final CustomerName customerName;
    private final List<RentalDocument> rentals;
    private final Amount totalAmount;
    private final FrequentRenterPoints frequentRenterPoints;

    private RentalsDocument(RentalRecord rentalRecord) {
        this.customerName = rentalRecord.customerName();
        this.rentals = asDocument(rentalRecord.rentals());
        this.totalAmount = rentalRecord.totalAmount();
        this.frequentRenterPoints = rentalRecord.frequentRenterPoints();
    }

    static RentalsDocument of(RentalRecord rentalRecord) {
        return new RentalsDocument(rentalRecord);
    }

    private List<RentalDocument> asDocument(List<Rental> rentals) {
        return rentals.stream().map(rental -> RentalDocument.with(rental.bookTitle(), rental.bookAuthors(), rental.daysRented(), rental.amount())).collect(Collectors.toList());
    }

    public CustomerName getCustomerName() {
        return customerName;
    }

    public List<RentalDocument> getRentals() {
        return rentals;
    }

    public Amount getTotalAmount() {
        return totalAmount;
    }

    public FrequentRenterPoints getFrequentRenterPoints() {
        return frequentRenterPoints;
    }
}
