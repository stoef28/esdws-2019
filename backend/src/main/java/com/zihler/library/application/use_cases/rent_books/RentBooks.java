package com.zihler.library.application.use_cases.rent_books;

import com.zihler.library.application.outbound_ports.persistance.CustomerRepository;
import com.zihler.library.application.use_cases.ports.IRentBooks;
import com.zihler.library.domain.entities.Customer;
import com.zihler.library.adapters.in_memory_persistance.InMemoryCustomerRepository;
import com.zihler.library.adapters.rest.RestRentalRecordPresenter;
import com.zihler.library.application.use_cases.rent_books.ports.RentBooksInput;
import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.RentalRecord;

import java.util.List;

public class RentBooks implements IRentBooks {
    private final CustomerRepository customerRepository;

    public RentBooks() {
        customerRepository = new InMemoryCustomerRepository();
    }

    @Override
    public List<String> executeWith(RentBooksInput rentBooksInput, RestRentalRecordPresenter presenter) {
        List<Rental> rentals = rentBooksInput.getRentals();

        Customer customer = customerRepository.findByUsername(rentBooksInput.getCustomerName());
        RentalRecord rentalRecord = new RentalRecord(customer, rentals);

        presenter.present(rentalRecord.asDocument());
        return presenter.presentation();
    }

}
