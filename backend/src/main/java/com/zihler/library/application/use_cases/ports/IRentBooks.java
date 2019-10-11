package com.zihler.library.application.use_cases.ports;

import com.zihler.library.adapters.rest.RestRentalRecordPresenter;
import com.zihler.library.application.use_cases.rent_books.ports.RentBooksInput;

import java.util.List;

public interface IRentBooks {
    List<String> executeWith(RentBooksInput rentBooksInput, RestRentalRecordPresenter presenter);
}
