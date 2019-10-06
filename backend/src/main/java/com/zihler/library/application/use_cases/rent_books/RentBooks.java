package com.zihler.library.application.use_cases.rent_books;

import com.zihler.library.InMemoryCustomerRepository;
import com.zihler.library.adapters.rest.RestRentalRecordPresenter;
import com.zihler.library.domain.entities.Customer;
import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.RentalRecord;
import com.zihler.library.application.use_cases.rent_books.ports.RentBooksInput;

import java.util.List;

public class RentBooks {
    private final InMemoryCustomerRepository customerRepository;

    public RentBooks(InMemoryCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void with(RentBooksInput rentBooksInput, RestRentalRecordPresenter restRentalRecordPresenter) {
        Customer customer = this.customerRepository.findByUsername(rentBooksInput.customerName());
        List<Rental> rentals = rentBooksInput.rentals();
        RentalRecord rentalRecord = RentalRecord.from(customer, rentals);
        restRentalRecordPresenter.present(rentalRecord);
    }

}
