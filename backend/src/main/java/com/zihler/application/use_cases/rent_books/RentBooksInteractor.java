package com.zihler.application.use_cases.rent_books;

import com.zihler.application.outbound_ports.persistence.CustomerRepository;
import com.zihler.application.outbound_ports.presentation.RentalRecordPresenter;
import com.zihler.application.use_cases.rent_books.ports.RentBooks;
import com.zihler.application.use_cases.rent_books.ports.RentBooksInput;
import com.zihler.domain.Rental;
import com.zihler.domain.RentalRecord;
import com.zihler.domain.entities.Customer;

import java.util.List;

public class RentBooksInteractor implements RentBooks {
    private final CustomerRepository customerRepository;

    public RentBooksInteractor(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void executeWith(RentBooksInput input, RentalRecordPresenter presenter) {
        Customer customer = customerRepository.findByName(input.customerName());
        List<Rental> rentals = input.rentals();
        RentalRecord rentalRecord = RentalRecord.from(customer, rentals);
        presenter.present(rentalRecord.asDocument());
    }

}
