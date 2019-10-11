package com.zihler.library.application.use_cases.rent_books.ports;

import com.zihler.library.application.outbound_ports.persistance.BookRepository;
import com.zihler.library.domain.values.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentBooksInput {
    private final BookRepository bookRepository;
    private final RentBooksRequest rentBooksRequest;

    public RentBooksInput(RentBooksRequest rentBooksRequest, BookRepository bookRepository) {
        this.rentBooksRequest = rentBooksRequest;
        this.bookRepository = bookRepository;
    }

    private RentBooksRequest getRentBooksRequest() {
        return rentBooksRequest;
    }

    public List<Rental> getRentals() {
        List<Rental> rentals = new ArrayList<>();
        for (RentBookRequest rentBookRequest : rentBooksRequest.getRentBookRequests()) {
            final Rental rental = new Rental(
                    bookRepository.findById(rentBookRequest.getBookId()),
                    rentBookRequest.getDaysRented());
            rentals.add(rental);
        }
        return rentals;
    }

    public String getCustomerName() {
        return getRentBooksRequest().getCustomerName();
    }
}
