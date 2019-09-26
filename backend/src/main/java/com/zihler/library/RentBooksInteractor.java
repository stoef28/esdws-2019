package com.zihler.library;

import java.util.List;

class RentBooksInteractor implements RentBooks {
    private final CustomerRepository customerRepository;
    private final RentalFactory rentalFactory;
    private RentalRecordPresenter rentalRecordPresenter;

    RentBooksInteractor(CustomerRepository customerRepository, RentalFactory rentalFactory) {
        this.customerRepository = customerRepository;
        this.rentalFactory = rentalFactory;
    }

    @Override
    public void rent(RentalsRequest rentalsRequest) {
        Customer customer = customerRepository.findByUsername(rentalsRequest.getCustomerName());
        List<Rental> rentals = rentalFactory.createRentalsFrom(rentalsRequest.getRentalRequests());
        RentalRecord rentalRecord = new RentalRecord(customer, rentals);
        RentalsResponse rentalsResponse = new RentalsResponse(rentalRecord);
        rentalRecordPresenter.present(rentalsResponse);
    }

    @Override
    public void setRentalRecordPresenter(RentalRecordPresenter rentalRecordPresenter) {
        this.rentalRecordPresenter = rentalRecordPresenter;
    }
}
