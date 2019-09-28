package com.zihler.application.use_cases.rent_books.ports;

import com.zihler.domain.CustomerName;
import com.zihler.domain.RentalsDocument;

import java.util.List;

public class RentalsRequest {
    private final CustomerName customerName;
    private final List<RentalRequest> rentalRequests;

    public RentalsRequest(CustomerName customerName, List<RentalRequest> rentalRequests) {
        this.customerName = customerName;
        this.rentalRequests = rentalRequests;
    }

    public CustomerName customerName() {
        return customerName;
    }

    public List<RentalRequest> getRentalRequests() {
        return rentalRequests;
    }
}
