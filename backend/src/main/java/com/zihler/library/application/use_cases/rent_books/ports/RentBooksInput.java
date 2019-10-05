package com.zihler.library.application.use_cases.rent_books.ports;

import com.zihler.library.application.outbound_ports.persistance.IRetrieveSingleBooks;
import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.values.CustomerName;
import com.zihler.library.domain.values.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentBooksInput {
    private IRetrieveSingleBooks iRetrieveSingleBooks;
    private final RentBooksRequest rentBooksRequest;

    public RentBooksInput(IRetrieveSingleBooks iRetrieveSingleBooks, RentBooksRequest rentBooksRequest) {
        this.iRetrieveSingleBooks = iRetrieveSingleBooks;
        this.rentBooksRequest = rentBooksRequest;
    }

    public CustomerName customerName() {
        return rentBooksRequest.getCustomerName();
    }

    public List<Rental> rentals() {
        List<Rental> rentals = new ArrayList<>();
        for (RentBookRequest rentBookRequest : rentBooksRequest.getRentBookRequests()) {
            Book book = iRetrieveSingleBooks.byId(rentBookRequest.getBookId());
            Rental rental = new Rental(book, rentBookRequest.getDaysRented());
            rentals.add(rental);
        }
        return rentals;
    }
}
