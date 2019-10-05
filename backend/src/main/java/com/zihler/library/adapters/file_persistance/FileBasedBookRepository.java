package com.zihler.library.adapters.file_persistance;

import com.zihler.library.application.outbound_ports.persistance.IRetrieveAllBooks;
import com.zihler.library.application.outbound_ports.persistance.IRetrieveSingleBooks;
import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.values.*;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FileBasedBookRepository implements IRetrieveAllBooks, IRetrieveSingleBooks {
    private final List<Book> books;

    public FileBasedBookRepository(ResourceLoader resourceLoader) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        resourceLoader.getResource("classpath:books.csv").getInputStream(),
                        StandardCharsets.UTF_8
                )
        );
        books = new ArrayList<>();
        while (bufferedReader.ready()) {
            final String line = bufferedReader.readLine();
            final String[] bookData = line.split(";");
            BookId bookId = BookId.from(bookData[0]);
            Title title = Title.from(bookData[1]);
            Authors authors = Authors.from(List.of(bookData[2].split(",")).stream().map(Author::new).collect(toList()));
            ReadingMode readingMode = ReadingMode.valueOf(bookData[3]);
            ThumbnailLink thumbnailLink = ThumbnailLink.from(bookData[4]);
            Book book = new Book(bookId, title, authors, readingMode, thumbnailLink);
            books.add(book);
        }
    }

    @Override
    public List<Book> retrieveAllBooks() {
        return books;
    }

    @Override
    public Book byId(BookId id) {
        return retrieveAllBooks().get(id.asInt());
    }
}
