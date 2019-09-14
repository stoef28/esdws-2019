package com.zihler.library.presenters;

import com.zihler.library.entities.Rental;
import com.zihler.library.entities.RentalReceipt;
import com.zihler.library.entities.RentalRecord;

import java.util.List;

public class StringRentalReceiptReceiptPresenter implements RentalReceiptPresenter {
    private RentalReceipt rentalReceipt;

    @Override
    public void present(RentalReceipt rentalReceipt) {
        this.rentalReceipt = rentalReceipt;
    }

    public String formatRentalReceipt() {
        if (rentalReceipt == null) {
            throw new RuntimeException("Present has not been called yet.");
        }
        return format(rentalReceipt);
    }

    private static String format(RentalReceipt rentalReceipt) {
        String result = appendHeader(rentalReceipt.getRentalRecord());
        result += appendRentals(rentalReceipt.getRentalRecord().getRentals());
        result += appendFooter(rentalReceipt.getRentalRecord());
        return result;
    }

    private static String appendFooter(RentalRecord rentalRecord) {
        String result = appendTotalAmount(rentalRecord);
        result += appendFrequentRenterPoints(rentalRecord);
        return result;
    }

    private static String appendFrequentRenterPoints(RentalRecord rentalRecord) {
        return "You earned " + rentalRecord.getFrequentRenterPoints() + " frequent renter points\n";
    }

    private static String appendTotalAmount(RentalRecord rentalRecord) {
        return "You owe " + rentalRecord.getTotalAmount() + " $\n";
    }

    private static String appendRentals(List<Rental> rentals) {
        String result = "";
        for (Rental rental : rentals) {
            // create figures for this rental
            result += "\t'" + rental.getBookTitle() + "' by '" + rental.getBookAuthors() + "' for " + rental.getDaysRented() + " days: \t" + rental.getAmount() + " $\n";
        }
        return result;
    }

    private static String appendHeader(RentalRecord rentalRecord) {
        return "Rental Record for " + rentalRecord.getCustomerName() + "\n";
    }
}
