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

    void rent(String customerName, List<String> rentalRequests) {
        Customer customer = customerRepository.findByUsername(customerName);
        List<Rental> rentals = rentalFactory.createRentalsFrom(rentalRequests);
        RentalRecord rentalRecord = new RentalRecord(customer, rentals);
        rentalRecordPresenter.present(rentalRecord);
    }
}
