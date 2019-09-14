package com.zihler.library.usecases;

import com.zihler.library.entities.Book;
import com.zihler.library.gateways.BookRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GatherBooksUseCaseInputPortAdapter implements GatherBooksUseCaseInputPort {
    private BookRepository bookRepository;
    private StringArrayBooksPresenter presenter;

    public GatherBooksUseCaseInputPortAdapter(BookRepository bookRepository, StringArrayBooksPresenter presenter) {
        this.bookRepository = bookRepository;
        this.presenter = presenter;
    }

    @Override
    public void gatherAll() {
        List<BookResponse> booksResponse = bookRepository.getAll()
                .stream()
                .map(BookResponse::from)
                .collect(toList());

        presenter.present(booksResponse);
    }
}
