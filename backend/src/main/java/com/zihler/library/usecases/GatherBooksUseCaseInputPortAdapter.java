package com.zihler.library.usecases;

import com.zihler.library.entities.Book;
import com.zihler.library.gateways.BookRepository;

import java.util.List;

public class GatherBooksUseCaseInputPortAdapter implements GatherBooksUseCaseInputPort {
    private BookRepository bookRepository;

    public GatherBooksUseCaseInputPortAdapter(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void gatherAllBooks(BooksPresenter presenter) {
        List<Book> allBooks = bookRepository.getAll();
        List<BookResponse> booksResponse = BookResponse.from(allBooks);
        presenter.present(booksResponse);
    }

}
