package com.zihler.domain;

import com.zihler.application.use_cases.rent_books.ports.RentalsRequest;

import java.util.List;
import java.util.stream.Collectors;

public class RentalsDocument {
    private final String customerName;
    private final List<RentalDocument> rentals;
    private final double totalAmount;
    private final int frequentRenterPoints;

    RentalsDocument(RentalRecord rentalRecord) {
        this.customerName = rentalRecord.getCustomerName();
        this.rentals = rentalRecord.getRentals().stream().map(rental -> new RentalDocument(rental.getBookTitle(), rental.getBookAuthors(), rental.getDaysRented(), rental.getAmount())).collect(Collectors.toList());
        this.totalAmount = rentalRecord.getTotalAmount();
        this.frequentRenterPoints = rentalRecord.getFrequentRenterPoints();
    }

    public static List<RentalsDocument> from(List<String> rentalRequests) {
        return null;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<RentalDocument> getRentals() {
        return rentals;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }
}
