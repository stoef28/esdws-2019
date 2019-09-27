package com.zihler.application.use_cases.rent_books;

import com.zihler.adapters.rest.RentBookInput;
import com.zihler.application.outbound_ports.persistence.CustomerRepository;
import com.zihler.application.outbound_ports.presentation.RentalRecordPresenter;
import com.zihler.application.use_cases.rent_books.ports.RentBooks;
import com.zihler.domain.Rental;
import com.zihler.domain.RentalRecord;
import com.zihler.domain.entities.Customer;

import java.util.List;

public class RentBooksInteractor implements RentBooks {
    private final CustomerRepository customerRepository;
    private final RentBookInput rentalInput;
    private RentalRecordPresenter rentalRecordPresenter;

    public RentBooksInteractor(CustomerRepository customerRepository, RentBookInput rentalInput, RentalRecordPresenter rentalRecordPresenter) {
        this.customerRepository = customerRepository;
        this.rentalInput = rentalInput;
        this.rentalRecordPresenter = rentalRecordPresenter;
    }

    @Override
    public void execute() {
        Customer customer = customerRepository.findByName(rentalInput.customerName());
        List<Rental> rentals = rentalInput.rentals();
        RentalRecord rentalRecord = RentalRecord.from(customer, rentals);
        rentalRecordPresenter.present(rentalRecord.asDocument());
    }

    @Override
    public void setRentalRecordPresenter(RentalRecordPresenter rentalRecordPresenter) {
        this.rentalRecordPresenter = rentalRecordPresenter;
    }
}
