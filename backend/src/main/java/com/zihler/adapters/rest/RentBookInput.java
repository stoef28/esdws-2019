package com.zihler.adapters.rest;

import com.zihler.application.outbound_ports.persistence.BookRepository;
import com.zihler.application.use_cases.rent_books.ports.RentalsRequest;
import com.zihler.domain.BookKey;
import com.zihler.domain.CustomerName;
import com.zihler.domain.DaysRented;
import com.zihler.domain.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentBookInput {
    private BookRepository bookRepository;

    public RentBookInput(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    Rental createRentalFrom(String nextRequest) {
        // dieser Teil sollte im Controller stattfinden
        final String[] rentalData = nextRequest.split(" ");
        BookKey bookKey = BookKey.from(Integer.parseInt(rentalData[0]));
        DaysRented daysRented = DaysRented.from(Integer.parseInt(rentalData[1]));

        // Nur in BookKey umwandeln.
        return new RentalRequest(
                bookKey,
                daysRented
        );
    }

    public List<Rental> createRentalsFrom(List<RentalRequest> rentalRequests) {
        // Dies gehört zum einen in den controller (Umwandlung von String in ValueObjects
        // zum anderen gehört die Umwandlung in Entities von BookKey, DaysRented zu Rentals ValueObjects in den UseCase
        List<Rental> rentals = new ArrayList<>();
        for (int i = 0; i < rentalRequests.size(); i++) {
            String nextRequest = rentalRequests.get(i);
            Rental rental = createRentalFrom(nextRequest);
            rentals.add(rental);
        }
        return rentals;
    }

    public List<Rental> rentals() {
        return null;
    }

    public CustomerName customerName() {
        return customerName;
    }
}
