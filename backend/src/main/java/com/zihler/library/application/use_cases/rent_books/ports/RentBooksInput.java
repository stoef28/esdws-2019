package com.zihler.library.application.use_cases.rent_books.ports;

import com.zihler.library.adapters.file_persistence.FileBasedBookRepository;
import com.zihler.library.domain.values.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentBooksInput {
    private final FileBasedBookRepository fileBasedBookRepository;
    private final RentBooksRequest rentBooksRequest;

    public RentBooksInput(RentBooksRequest rentBooksRequest, FileBasedBookRepository fileBasedBookRepository) {
        this.rentBooksRequest = rentBooksRequest;
        this.fileBasedBookRepository = fileBasedBookRepository;
    }

    private RentBooksRequest getRentBooksRequest() {
        return rentBooksRequest;
    }

    public List<Rental> getRentals() {
        List<Rental> rentals = new ArrayList<>();
        for (RentBookRequest rentBookRequest : rentBooksRequest.getRentBookRequests()) {
            final Rental rental = new Rental(
                    fileBasedBookRepository.findById(rentBookRequest.getBookId()),
                    rentBookRequest.getDaysRented());
            rentals.add(rental);
        }
        return rentals;
    }

    public String getCustomerName() {
        return getRentBooksRequest().getCustomerName();
    }
}
