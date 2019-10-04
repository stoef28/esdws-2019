package com.zihler.library.adapters.rest;

import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.RentalRecord;

import java.util.List;

public class RestRentalRecordPresenter {
    private List<String> restRentalRecord;

    public RestRentalRecordPresenter() {
    }

    public void present(RentalRecord rentalRecord) {
        String result = "Rental Record for " + rentalRecord.customerName() + "\n";
        result += format(rentalRecord.rentals());
        // add footer lines
        result += "You owe " + rentalRecord.totalAmount() + " $\n";
        result += "You earned " + rentalRecord.frequentRenterPoints() + " frequent renter points\n";

        this.restRentalRecord = List.of(result);
    }

    private String format(List<Rental> rentals) {
        String result = "";
        for (Rental rental : rentals) {
            // create figures for this rental
            result += "\t'" + rental.bookTitle() + "' by '" + rental.bookAuthors() + "' for " + rental.daysRented() + " days: \t" + rental.amount() + " $\n";
        }
        return result;
    }

    public List<String> presentation() {
        return restRentalRecord;
    }
}
