package com.zihler.library.entities;

import com.zihler.library.gateways.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class RentalFactory {
    private BookRepository bookRepository;

    public RentalFactory(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    Rental createRentalFrom(String nextRequest) {
        final String[] rentalData = nextRequest.split(" ");

        return new Rental(
                this.bookRepository.getByKey(Integer.parseInt(rentalData[0])),
                Integer.parseInt(rentalData[1])
        );
    }

    public List<Rental> from(List<String> rentalRequests) {
        List<Rental> rentals = new ArrayList<>();
        for (int i = 0; i < rentalRequests.size(); i++) {
            String nextRequest = rentalRequests.get(i);
            rentals.add(createRentalFrom(nextRequest));
        }
        return rentals;
    }
}
