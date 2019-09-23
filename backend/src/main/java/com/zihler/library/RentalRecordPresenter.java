package com.zihler.library;

import java.util.List;

class RentalRecordPresenter {
    private List<String> rentalsResponse;

    void present(RentalRecord rentalRecord) {
        String formattedRentals = "Rental Record for " + rentalRecord.getCustomerName() + "\n";
        formattedRentals += formatRentals(rentalRecord.getRentals());
        // add footer lines
        formattedRentals += "You owe " + rentalRecord.getTotalAmount() + " $\n";
        formattedRentals += "You earned " + rentalRecord.getFrequentRenterPoints() + " frequent renter points\n";

        this.rentalsResponse = List.of(formattedRentals);
    }

    private String formatRentals(List<Rental> rentals) {
        String result = "";
        for (Rental rental : rentals) {
            // create figures for this rental
            result += "\t'" + rental.getBookTitle() + "' by '" + rental.getBookAuthors() + "' for " + rental.getDaysRented() + " days: \t" + rental.getAmount() + " $\n";
        }
        return result;
    }

    List<String> getRentalsResponse() {
        return this.rentalsResponse;
    }
}
