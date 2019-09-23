package com.zihler.library;

import java.util.ArrayList;
import java.util.List;

class StringRentalRecordPresenter {
    private List<String> rentalsResponse;

    void present(RentalsResponse rentalsResponse) {
        String formattedRentals = "Rental Record for " + rentalsResponse.getCustomerName() + "\n";
        formattedRentals += formatRentals(rentalsResponse.getRentals());
        // add footer lines
        formattedRentals += "You owe " + rentalsResponse.getTotalAmount() + " $\n";
        formattedRentals += "You earned " + rentalsResponse.getFrequentRenterPoints() + " frequent renter points\n";

        this.rentalsResponse = List.of(formattedRentals);
    }

    private String formatRentals(List<RentalResponse> rentalResponses) {
        String result = "";
        for (RentalResponse rental : rentalResponses) {
            // create figures for this rental
            result += "\t'" + rental.getBookTitle() + "' by '" + rental.getBookAuthors() + "' for " + rental.getDaysRented() + " days: \t" + rental.getAmount() + " $\n";
        }
        return result;
    }

    List<String> getRentalsResponse() {
        return this.rentalsResponse;
    }
}
