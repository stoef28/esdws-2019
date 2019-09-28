package com.zihler.adapters.rest;

import com.zihler.application.outbound_ports.presentation.RentalRecordPresenter;
import com.zihler.domain.RentalDocument;
import com.zihler.domain.RentalsDocument;

import java.util.List;

class RentalRecordRestPresenter implements RentalRecordPresenter {
    private List<String> rentalsResponse;

    @Override
    public void present(RentalsDocument rentalsResponse) {
        String formattedRentals = "Rental Record for " + rentalsResponse.getCustomerName() + "\n";
        formattedRentals += formatRentals(rentalsResponse.getRentals());
        // add footer lines
        formattedRentals += "You owe " + rentalsResponse.getTotalAmount() + " $\n";
        formattedRentals += "You earned " + rentalsResponse.getFrequentRenterPoints() + " frequent renter points\n";

        this.rentalsResponse = List.of(formattedRentals);
    }

    private String formatRentals(List<RentalDocument> rentalResponses) {
        String result = "";
        for (RentalDocument rental : rentalResponses) {
            // create figures for this rental
            result += "\t'" + rental.getBookTitle() + "' by '" + rental.getBookAuthors() + "' for " + rental.getDaysRented() + " days: \t" + rental.getAmount() + " $\n";
        }
        return result;
    }

    List<String> presentation() {
        return this.rentalsResponse;
    }
}
