package com.zihler.library.usecases;

import com.zihler.library.entities.Book;
import com.zihler.library.gateways.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GatherBooksUseCaseInputPortAdapter implements GatherBooksUseCaseInputPort {
    private BookRepository bookRepository;

    public GatherBooksUseCaseInputPortAdapter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<String[]> getBooks() {
        return bookRepository.getAll()
                .stream()
                .map(Book::toStringArray)
                .collect(Collectors.toList());
    }
}
