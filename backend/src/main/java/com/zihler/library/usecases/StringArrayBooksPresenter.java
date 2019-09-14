package com.zihler.library.usecases;

import java.util.List;
import java.util.stream.Collectors;

public class StringArrayBooksPresenter implements BooksPresenter {
    private List<BookResponse> booksResponse;

    @Override
    public void present(List<BookResponse> booksResponse) {
        this.booksResponse = booksResponse;
    }

    public List<String[]> formatBooksForViewModel() {
        return booksResponse.stream()
                .map(this::toStringArray)
                .collect(Collectors.toList());
    }

    private String[] toStringArray(BookResponse book) {
        return new String[]{
                book.getKey() + "",
                book.getTitle(),
                book.getAuthors(),
                book.getReadingMode(),
                book.getLink()
        };
    }
}
