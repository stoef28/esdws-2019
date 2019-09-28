package com.zihler.application.use_cases.rent_books.ports;

import com.zihler.application.outbound_ports.persistence.BookRepository;
import com.zihler.domain.CustomerName;
import com.zihler.domain.Rental;
import com.zihler.domain.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class RentBooksInput {
    private RentalsRequest rentalsRequest;
    private BookRepository bookRepository;

    public RentBooksInput(BookRepository bookRepository, RentalsRequest rentalsRequest) {
        this.bookRepository = bookRepository;
        this.rentalsRequest = rentalsRequest;
    }

    public CustomerName customerName() {
        return rentalsRequest.customerName();
    }

    public List<Rental> rentals() {
        List<Rental> rentals = new ArrayList<>();
        for (RentalRequest rentalRequest : rentalsRequest.getRentalRequests()) {
            Book book = bookRepository.getByKey(rentalRequest.getBookKey());
            Rental rental = Rental.from(book, rentalRequest.getDaysRented());
            rentals.add(rental);
        }
        return rentals;
    }
}
