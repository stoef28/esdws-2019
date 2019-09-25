package com.zihler.library;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();

    Book getByKey(int bookKey);
}
