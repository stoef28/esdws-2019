package com.zihler.library.usecases;

import com.zihler.library.entities.*;
import com.zihler.library.gateways.BookRepository;
import com.zihler.library.gateways.CustomerRepository;
import com.zihler.library.presenters.RentalRecordPresenter;

import java.util.List;

public class RentBooksUseCaseInputPortAdapter implements RentBooksUseCaseInputPort {
    private final RentalFactory rentals;
    private final CustomerRepository customerRepository;
    private final RentalRecordPresenter rentalPresenter;

    public RentBooksUseCaseInputPortAdapter(CustomerRepository customerRepository, BookRepository bookRepository, RentalRecordPresenter rentalPresenter) {
        this.rentals = new RentalFactory(bookRepository);
        this.customerRepository = customerRepository;
        this.rentalPresenter = rentalPresenter;
    }

    @Override
    public void rent(RentalRequest rentalRequest) {
        Customer customer = customerRepository.findByUsername(rentalRequest.getUsername());
        List<Rental> rentals = this.rentals.from(rentalRequest.getBooksToRent());
        RentalRecord rentalRecord = new RentalRecord(customer, rentals);
        RentalRecordResponse response = RentalRecordResponse.from(rentalRecord);
        rentalPresenter.present(response);
    }
}
