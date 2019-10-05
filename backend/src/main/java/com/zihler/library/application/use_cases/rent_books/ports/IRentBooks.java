package com.zihler.library.application.use_cases.rent_books.ports;

import com.zihler.library.application.outbound_ports.presentation.IPresentRentalRecords;

public interface IRentBooks {
    void with(RentBooksInput rentBooksInput, IPresentRentalRecords IPresentRentalRecords);
}
