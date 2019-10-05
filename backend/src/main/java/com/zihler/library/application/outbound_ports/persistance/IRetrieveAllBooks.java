package com.zihler.library.application.outbound_ports.persistance;

import com.zihler.library.domain.entities.Book;

import java.util.List;

public interface IRetrieveAllBooks {
    List<Book> retrieveAllBooks();
}
