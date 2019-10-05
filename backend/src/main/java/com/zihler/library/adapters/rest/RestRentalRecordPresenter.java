package com.zihler.library.adapters.rest;

import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.RentalDocument;
import com.zihler.library.domain.values.RentalRecord;
import com.zihler.library.domain.values.RentalRecordDocument;

import java.util.List;

public class RestRentalRecordPresenter {
    private List<String> restRentalRecord;

    public RestRentalRecordPresenter() {
    }

    public void present(RentalRecordDocument rentalRecordDocument) {
        String result = "Rental Record for " + rentalRecordDocument.customerName() + "\n";
        result += format(rentalRecordDocument.rentals());
        // add footer lines
        result += "You owe " + rentalRecordDocument.totalAmount() + " $\n";
        result += "You earned " + rentalRecordDocument.frequentRenterPoints() + " frequent renter points\n";

        this.restRentalRecord = List.of(result);
    }

    private String format(List<RentalDocument> rentals) {
        String result = "";
        for (RentalDocument rental : rentals) {
            // create figures for this rental
            result += "\t'" + rental.bookTitle() + "' by '" + rental.bookAuthors() + "' for " + rental.daysRented() + " days: \t" + rental.amount() + " $\n";
        }
        return result;
    }

    public List<String> presentation() {
        return restRentalRecord;
    }
}
