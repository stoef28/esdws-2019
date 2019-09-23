package com.zihler.library;

import java.util.List;

class RentBooksInteractor {
    private final InMemoryCustomerRepository customerRepository;
    private final RentalFactory rentalFactory;
    private StringRentalRecordPresenter rentalRecordPresenter;

    RentBooksInteractor(InMemoryCustomerRepository customerRepository, RentalFactory rentalFactory, StringRentalRecordPresenter rentalRecordPresenter) {
        this.customerRepository = customerRepository;
        this.rentalFactory = rentalFactory;
        this.rentalRecordPresenter = rentalRecordPresenter;
    }

    void rent(RentalsRequest rentalsRequest) {
        Customer customer = customerRepository.findByUsername(rentalsRequest.getCustomerName());
        List<Rental> rentals = rentalFactory.createRentalsFrom(rentalsRequest.getRentalRequests());
        RentalRecord rentalRecord = new RentalRecord(customer, rentals);
        RentalsResponse rentalsResponse = new RentalsResponse(rentalRecord);
        rentalRecordPresenter.present(rentalsResponse);
    }
}
