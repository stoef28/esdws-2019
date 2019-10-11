package com.zihler.library.application.use_cases.rent_books;

import com.zihler.library.Customer;
import com.zihler.library.InMemoryCustomerRepository;
import com.zihler.library.adapters.file_persistence.FileBasedBookRepository;
import com.zihler.library.adapters.rest.RestRentalRecordPresenter;
import com.zihler.library.application.use_cases.rent_books.ports.RentBookRequest;
import com.zihler.library.application.use_cases.rent_books.ports.RentBooksRequest;
import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.RentalRecord;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class RentBooks {
    private final InMemoryCustomerRepository customerRepository;
    private final FileBasedBookRepository fileBasedBookRepository;

    public RentBooks(ResourceLoader resourceLoader) {
        customerRepository = new InMemoryCustomerRepository();
        fileBasedBookRepository = new FileBasedBookRepository(resourceLoader);
    }

    public List<String> executeWith(RentBooksRequest rentBooksRequest, RestRentalRecordPresenter presenter) {
        List<Rental> rentals = getRentals(rentBooksRequest.getRentBookRequests());

        Customer customer = customerRepository.findByUsername(rentBooksRequest.getCustomerName());
        RentalRecord rentalRecord = new RentalRecord(customer, rentals);

        presenter.present(rentalRecord);
        return presenter.presentation();
    }

    private List<Rental> getRentals(@RequestBody List<RentBookRequest> rentBooksRequests) {
        List<Rental> rentals = new ArrayList<>();
        for (RentBookRequest rentBookRequest : rentBooksRequests) {
            final Rental rental = new Rental(
                    fileBasedBookRepository.findById(rentBookRequest.getBookId()),
                    rentBookRequest.getDaysRented());
            rentals.add(rental);
        }
        return rentals;
    }
}
