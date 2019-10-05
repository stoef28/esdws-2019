package com.zihler.library.application.outbound_ports.persistence;

import com.zihler.library.domain.entities.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();

    Book findById(int bookId);
}
