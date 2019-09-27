package com.zihler.application.outbound_ports.persistence;

import com.zihler.domain.BookKey;
import com.zihler.domain.entities.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();

    Book getByKey(BookKey bookKey);
}
