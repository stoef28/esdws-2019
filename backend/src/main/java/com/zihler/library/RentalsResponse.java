package com.zihler.library;

import java.util.List;
import java.util.stream.Collectors;

class RentalsResponse {
    private final String customerName;
    private final List<RentalResponse> rentals;
    private final double totalAmount;
    private final int frequentRenterPoints;

    RentalsResponse(RentalRecord rentalRecord) {
        this.customerName = rentalRecord.getCustomerName();
        this.rentals = rentalRecord.getRentals().stream().map(rental -> new RentalResponse(rental.getBookTitle(), rental.getBookAuthors(), rental.getDaysRented(), rental.getAmount())).collect(Collectors.toList());
        this.totalAmount = rentalRecord.getTotalAmount();
        this.frequentRenterPoints = rentalRecord.getFrequentRenterPoints();
    }

    String getCustomerName() {
        return customerName;
    }

    List<RentalResponse> getRentals() {
        return rentals;
    }

    double getTotalAmount() {
        return totalAmount;
    }

    int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }
}
