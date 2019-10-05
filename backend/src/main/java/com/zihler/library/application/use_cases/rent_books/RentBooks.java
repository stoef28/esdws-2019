package com.zihler.library.application.use_cases.rent_books;

import com.zihler.library.application.outbound_ports.persistance.IFindCustomers;
import com.zihler.library.application.outbound_ports.presentation.IPresentRentalRecords;
import com.zihler.library.application.use_cases.rent_books.ports.IRentBooks;
import com.zihler.library.domain.entities.Customer;
import com.zihler.library.domain.values.Rental;
import com.zihler.library.domain.values.RentalRecord;
import com.zihler.library.domain.values.RentalRecordDocument;
import com.zihler.library.application.use_cases.rent_books.ports.RentBooksInput;

import java.util.List;

public class RentBooks implements IRentBooks {
    private final IFindCustomers iFindCustomers;

    public RentBooks(IFindCustomers iFindCustomers) {
        this.iFindCustomers = iFindCustomers;
    }

    @Override
    public void with(RentBooksInput rentBooksInput, IPresentRentalRecords IPresentRentalRecords) {
        Customer customer = iFindCustomers.byName(rentBooksInput.customerName());
        List<Rental> rentals = rentBooksInput.rentals();
        RentalRecord rentalRecord = RentalRecord.from(customer, rentals);
        RentalRecordDocument rentalRecordDocument = rentalRecord.asDocument();
        IPresentRentalRecords.present(rentalRecordDocument);
    }

}
