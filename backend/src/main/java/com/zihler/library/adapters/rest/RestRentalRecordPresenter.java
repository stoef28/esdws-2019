package com.zihler.library.adapters.rest;

import com.zihler.library.application.outbound_ports.presentation.RentalRecordPresenter;
import com.zihler.library.domain.values.RentalDocument;
import com.zihler.library.domain.values.RentalRecordDocument;

import java.util.List;

public class RestRentalRecordPresenter implements RentalRecordPresenter {

    private List<String> restRentalRecord;

    @Override
    public void present(RentalRecordDocument rentalRecordDocument) {
        String result = "Rental Record for " + rentalRecordDocument.getCustomerName() + "\n";
        result += format(rentalRecordDocument.getRentals());
        // add footer lines
        result += "You owe " + rentalRecordDocument.getTotalAmount() + " $\n";
        result += "You earned " + rentalRecordDocument.getFrequentRenterPoints() + " frequent renter points\n";

        restRentalRecord = List.of(result);
    }

    public List<String> presentation(){
        return restRentalRecord;
    }

    public String format(List<RentalDocument> rentalDocuments) {
        StringBuilder result = new StringBuilder();
        for (RentalDocument rentalDocument : rentalDocuments) {
            // create figures for this rental
            result.append("\t'")
                    .append(rentalDocument.getBookTitle())
                    .append("' by '")
                    .append(rentalDocument.getBookAuthors())
                    .append("' for ")
                    .append(rentalDocument.getDaysRented())
                    .append(" days: \t")
                    .append(rentalDocument.getAmount())
                    .append(" $\n");
        }
        return result.toString();
    }
}
