package com.zihler.application.use_cases.rent_books.ports;

import com.zihler.application.outbound_ports.presentation.RentalRecordPresenter;

public interface RentBooks {
    void execute();

    void setRentalRecordPresenter(RentalRecordPresenter rentalRecordPresenter);
}
