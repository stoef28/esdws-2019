package com.zihler.library.application.outbound_ports.persistance;

import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.values.BookId;

public interface IRetrieveSingleBooks {
    Book byId(BookId id);
}
