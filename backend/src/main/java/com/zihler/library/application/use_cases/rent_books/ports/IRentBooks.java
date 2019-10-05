package com.zihler.library.application.use_cases.rent_books.ports;

import com.zihler.library.application.outbound_ports.presentation.RentalRecordPresenter;

public interface IRentBooks {
    void executeWith(RentBooksInput input, RentalRecordPresenter presenter);
}
