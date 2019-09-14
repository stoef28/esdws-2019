package com.zihler.library.gateways;

import com.zihler.library.entities.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();

    Book getByKey(int key);
}
