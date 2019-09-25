package com.zihler.library;

import java.util.ArrayList;
import java.util.List;

public class RentalFactory {
    private BookRepository bookRepository;

    public RentalFactory(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    Rental createRentalFrom(String nextRequest) {
        final String[] rentalData = nextRequest.split(" ");
        int bookKey = Integer.parseInt(rentalData[0]);
        return new Rental(
                bookRepository.getByKey(bookKey),
                Integer.parseInt(rentalData[1])
        );
    }

    List<Rental> createRentalsFrom(List<String> rentalRequests) {
        List<Rental> rentals = new ArrayList<>();
        for (int i = 0; i < rentalRequests.size(); i++) {
            String nextRequest = rentalRequests.get(i);
            Rental rental = createRentalFrom(nextRequest);
            rentals.add(rental);
        }
        return rentals;
    }
}
