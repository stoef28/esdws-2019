package com.zihler.library.application.use_cases.rent_books.ports;

import com.zihler.library.adapters.rest.RestRentalRecordPresenter;

public interface IRentBooks {
    void with(RentBooksInput rentBooksInput, RestRentalRecordPresenter restRentalRecordPresenter);
}
