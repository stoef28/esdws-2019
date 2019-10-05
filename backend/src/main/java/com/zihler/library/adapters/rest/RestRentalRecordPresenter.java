package com.zihler.library.adapters.rest;

import com.zihler.library.application.outbound_ports.presentation.RentalRecordPresenter;
import com.zihler.library.domain.values.RentalDocument;
import com.zihler.library.domain.values.RentalRecordDocument;

import java.util.List;

public class RestRentalRecordPresenter implements RentalRecordPresenter {
    private List<String> restRentalRecord;

    public RestRentalRecordPresenter() {
    }

    @Override
    public void present(RentalRecordDocument rentalRecord) {
        String result = "Rental Record for " + rentalRecord.getCustomerName() + "\n";
        result += format(rentalRecord.getRentals());
        // add footer lines
        result += "You owe " + rentalRecord.getTotalAmount() + " $\n";
        result += "You earned " + rentalRecord.getFrequentRenterPoints() + " frequent renter points\n";

        this.restRentalRecord = List.of(result);
    }

    private String format(List<RentalDocument> rentals) {
        String result = "";
        for (RentalDocument rental : rentals) {
            // create figures for this rental
            result += "\t'" + rental.getBookTitle() + "' by '" + rental.getBookAuthors() + "' for " + rental.getDaysRented() + " days: \t" + rental.getAmount() + " $\n";
        }
        return result;
    }

    public List<String> presentation() {
        return restRentalRecord;
    }
}
