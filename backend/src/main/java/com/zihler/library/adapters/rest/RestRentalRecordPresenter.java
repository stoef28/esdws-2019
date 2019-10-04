package com.zihler.library.adapters.rest;

import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.RentalRecord;

import java.util.List;

public class RestRentalRecordPresenter {
    private List<String> restRentalRecord;

    public RestRentalRecordPresenter() {
    }

    public void present(RentalRecord rentalRecord) {
        String result = "Rental Record for " + rentalRecord.getCustomerName() + "\n";
        result += format(rentalRecord.getRentals());
        // add footer lines
        result += "You owe " + rentalRecord.getTotalAmount() + " $\n";
        result += "You earned " + rentalRecord.getFrequentRenterPoints() + " frequent renter points\n";

        this.restRentalRecord = List.of(result);
    }

    private String format(List<Rental> rentals) {
        String result = "";
        for (Rental rental : rentals) {
            // create figures for this rental
            result += "\t'" + rental.getBookTitle() + "' by '" + rental.getBookAuthors() + "' for " + rental.getDaysRented() + " days: \t" + rental.getAmount() + " $\n";
        }
        return result;
    }

    public List<String> presentation() {
        return restRentalRecord;
    }
}
