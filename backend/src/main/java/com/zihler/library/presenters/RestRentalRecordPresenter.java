package com.zihler.library.presenters;

import com.zihler.library.usecases.RentalRecordResponse;
import com.zihler.library.usecases.RentalResponse;

import java.util.List;

public class RestRentalRecordPresenter implements RentalRecordPresenter {
    private RentalRecordResponse rentalRecordResponse;

    @Override
    public void present(RentalRecordResponse rentalRecordResponse) {
        this.rentalRecordResponse = rentalRecordResponse;
    }

    public List<String> formatRentalReceiptForViewModel() {
        if (rentalRecordResponse == null) {
            throw new RuntimeException("Present has not been called yet.");
        }
        return List.of(formatForViewModel());
    }

    private String formatForViewModel() {
        String result = appendHeader();
        result += appendRentals();
        result += appendFooter();
        return result;
    }

    private String appendFooter() {
        String result = appendTotalAmount();
        result += appendFrequentRenterPoints();
        return result;
    }

    private String appendFrequentRenterPoints() {
        return "You earned " + rentalRecordResponse.getFrequentRenterPoints() + " frequent renter points\n";
    }

    private String appendTotalAmount() {
        return "You owe " + rentalRecordResponse.getTotalAmount() + " $\n";
    }

    private String appendRentals() {
        String result = "";
        for (RentalResponse rental : rentalRecordResponse.getRentals()) {
            // create figures for this rental
            result += "\t'" + rental.getBookTitle() + "' by '" + rental.getBookAuthors() + "' for " + rental.getDaysRented() + " days: \t" + rental.getAmount() + " $\n";
        }
        return result;
    }

    private String appendHeader() {
        return "Rental Record for " + rentalRecordResponse.getCustomerName() + "\n";
    }

}
