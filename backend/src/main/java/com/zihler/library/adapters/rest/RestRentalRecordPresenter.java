package com.zihler.library.adapters.rest;

import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.RentalRecord;

import java.util.List;

public class RestRentalRecordPresenter {

    private List<String> restRentalRecord;

    public void present(RentalRecord rentalRecord) {
        String result = "Rental Record for " + rentalRecord.getCustomerName() + "\n";
        result += format(rentalRecord.getRentals());
        // add footer lines
        result += "You owe " + rentalRecord.getTotalAmount() + " $\n";
        result += "You earned " + rentalRecord.getFrequentRenterPoints() + " frequent renter points\n";

        restRentalRecord = List.of(result);
    }

    public List<String> presentation(){
        return restRentalRecord;
    }

    public String format(List<Rental> rentals) {
        StringBuilder result = new StringBuilder();
        for (Rental rental : rentals) {
            // create figures for this rental
            result.append("\t'")
                    .append(rental.getBookTitle())
                    .append("' by '")
                    .append(rental.getBookAuthors())
                    .append("' for ")
                    .append(rental.getDaysRented())
                    .append(" days: \t")
                    .append(rental.getAmount())
                    .append(" $\n");
        }
        return result.toString();
    }
}
