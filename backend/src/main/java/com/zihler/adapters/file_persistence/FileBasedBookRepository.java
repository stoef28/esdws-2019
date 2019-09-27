package com.zihler.adapters.file_persistence;

import com.zihler.application.outbound_ports.persistence.BookRepository;
import com.zihler.domain.BookKey;
import com.zihler.domain.entities.Book;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileBasedBookRepository implements BookRepository {
    private final List<Book> books;

    public FileBasedBookRepository(ResourceLoader resourceLoader) throws IOException {
        this.books = loadBooks(resourceLoader);
    }

    @Override
    public List<Book> getAll() {
        return books;
    }

    private List<Book> loadBooks(ResourceLoader resourceLoader) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        resourceLoader.getResource("classpath:books.csv").getInputStream(),
                        StandardCharsets.UTF_8
                )
        );
        final List<Book> books = new ArrayList<>();
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            Book book = Book.from(line.split(";"));
            books.add(book);
        }
        return books;
    }

    @Override
    public Book getByKey(BookKey bookKey) {
        return this.books.get(bookKey.get());
    }
}
