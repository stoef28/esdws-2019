package com.zihler.library.usecases;

import com.zihler.library.entities.RentalRecord;

import java.util.List;

public class RentalRecordResponse {
    private List<RentalResponse> rentals;
    private String customerName;
    private int frequentRenterPoints;
    private double totalAmount;

    public static RentalRecordResponse from(RentalRecord rentalRecord) {
        RentalRecordResponse rentalRecordResponse = new RentalRecordResponse();

        rentalRecordResponse.rentals = RentalResponse.toRentalResponses(rentalRecord.getRentals());
        rentalRecordResponse.customerName = rentalRecord.getCustomerName();
        rentalRecordResponse.frequentRenterPoints = rentalRecord.getFrequentRenterPoints();
        rentalRecordResponse.totalAmount = rentalRecord.getTotalAmount();

        return rentalRecordResponse;
    }

    public List<RentalResponse> getRentals() {
        return rentals;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getFrequentRenterPoints() {
        return frequentRenterPoints;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
