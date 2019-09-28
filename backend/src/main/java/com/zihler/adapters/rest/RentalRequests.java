package com.zihler.adapters.rest;

import com.zihler.application.use_cases.rent_books.ports.RentalRequest;
import com.zihler.domain.BookKey;
import com.zihler.domain.DaysRented;

import java.util.ArrayList;
import java.util.List;

public class RentalRequests {


    private static RentalRequest from(String nextRequest) {
        final String[] rentalData = nextRequest.split(" ");
        BookKey bookKey = BookKey.from(Integer.parseInt(rentalData[0]));
        DaysRented daysRented = DaysRented.from(Integer.parseInt(rentalData[1]));

        return new RentalRequest(bookKey, daysRented);
    }

    public static List<RentalRequest> from(List<String> rentalRequests) {
        List<RentalRequest> rentals = new ArrayList<>();
        for (int i = 0; i < rentalRequests.size(); i++) {
            String nextRequest = rentalRequests.get(i);
            RentalRequest rental = from(nextRequest);
            rentals.add(rental);
        }
        return rentals;
    }

}
