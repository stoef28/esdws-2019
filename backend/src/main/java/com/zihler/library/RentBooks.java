package com.zihler.library;

public interface RentBooks {
    void rent(RentalsRequest rentalsRequest);

    void setRentalRecordPresenter(RentalRecordPresenter rentalRecordPresenter);
}
