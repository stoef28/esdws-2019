package com.zihler.library.usecases;

import com.zihler.library.entities.*;
import com.zihler.library.gateways.BookRepository;
import com.zihler.library.gateways.CustomerRepository;
import com.zihler.library.presenters.RentalReceiptPresenter;

import java.util.List;

public class RentBooksUseCaseInputPortAdapter implements RentBooksUseCaseInputPort {
    private final RentalFactory rentals;
    private final CustomerRepository customerRepository;
    private final RentalReceiptPresenter rentalPresenter;

    public RentBooksUseCaseInputPortAdapter(CustomerRepository customerRepository, BookRepository bookRepository, RentalReceiptPresenter rentalPresenter) {
        this.rentals = new RentalFactory(bookRepository);
        this.customerRepository = customerRepository;
        this.rentalPresenter = rentalPresenter;
    }

    @Override
    public void rent(List<String> booksToRent, String username) {
        Customer customer = customerRepository.findByUsername(username);
        List<Rental> rentals = this.rentals.from(booksToRent);
        RentalRecord rentalRecord = new RentalRecord(customer, rentals);
        RentalReceipt rentalReceipt = RentalReceipt.of(rentalRecord);
        rentalPresenter.present(rentalReceipt);
    }
}
