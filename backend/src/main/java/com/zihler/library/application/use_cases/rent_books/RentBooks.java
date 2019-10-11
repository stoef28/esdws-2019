package com.zihler.library.application.use_cases.rent_books;

import com.zihler.library.Customer;
import com.zihler.library.InMemoryCustomerRepository;
import com.zihler.library.adapters.rest.RestRentalRecordPresenter;
import com.zihler.library.application.use_cases.rent_books.ports.RentBooksInput;
import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.RentalRecord;

import java.util.List;

public class RentBooks {
    private final InMemoryCustomerRepository customerRepository;

    public RentBooks() {
        customerRepository = new InMemoryCustomerRepository();
    }

    public List<String> executeWith(RentBooksInput rentBooksInput, RestRentalRecordPresenter presenter) {
        List<Rental> rentals = rentBooksInput.getRentals();

        Customer customer = customerRepository.findByUsername(rentBooksInput.getCustomerName());
        RentalRecord rentalRecord = new RentalRecord(customer, rentals);

        presenter.present(rentalRecord);
        return presenter.presentation();
    }

}
