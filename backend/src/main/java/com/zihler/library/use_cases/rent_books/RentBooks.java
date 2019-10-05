package com.zihler.library.use_cases.rent_books;

import com.zihler.library.Customer;
import com.zihler.library.InMemoryCustomerRepository;
import com.zihler.library.adapters.rest.RestRentalRecordPresenter;
import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.RentalRecord;
import com.zihler.library.domain.values.RentalRecordDocument;
import com.zihler.library.use_cases.rent_books.ports.RentBooksInput;

import java.util.List;

public class RentBooks {
    private final InMemoryCustomerRepository customerRepository;

    public RentBooks(InMemoryCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void executeWith(RentBooksInput input, RestRentalRecordPresenter restRentalRecordPresenter) {
        Customer customer = this.customerRepository.findByUsername(input.getCustomerName());
        List<Rental> rentals = input.getRentals();
        RentalRecord rentalRecord = RentalRecord.from(customer, rentals);
        RentalRecordDocument rentalRecordDocument = rentalRecord.asDocument();
        restRentalRecordPresenter.present(rentalRecordDocument);
    }

}
