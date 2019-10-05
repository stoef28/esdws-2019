package com.zihler.library.application.use_cases.rent_books.ports;

import com.zihler.library.adapters.file_persistance.FileBasedBookRepository;
import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.values.CustomerName;
import com.zihler.library.domain.values.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentBooksInput {
    private FileBasedBookRepository bookRepository;
    private final RentBooksRequest rentBooksRequest;

    public RentBooksInput(FileBasedBookRepository bookRepository, RentBooksRequest rentBooksRequest) {
        this.bookRepository = bookRepository;
        this.rentBooksRequest = rentBooksRequest;
    }

    public CustomerName customerName() {
        return rentBooksRequest.getCustomerName();
    }

    public List<Rental> rentals() {
        List<Rental> rentals = new ArrayList<>();
        for (RentBookRequest rentBookRequest : rentBooksRequest.getRentBookRequests()) {
            Book book = this.bookRepository.findById(rentBookRequest.getBookId());
            Rental rental = new Rental(book, rentBookRequest.getDaysRented());
            rentals.add(rental);
        }
        return rentals;
    }
}
