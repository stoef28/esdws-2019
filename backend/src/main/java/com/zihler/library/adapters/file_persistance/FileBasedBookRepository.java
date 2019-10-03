package com.zihler.library.adapters.file_persistance;

import com.zihler.library.domain.entities.Book;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileBasedBookRepository {
    private final List<Book> books;

    public FileBasedBookRepository(ResourceLoader resourceLoader) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        resourceLoader.getResource("classpath:books.csv").getInputStream(),
                        StandardCharsets.UTF_8
                )
        );
        this.books = new ArrayList<>();
        while (bufferedReader.ready()) {
            final String line = bufferedReader.readLine();
            final String[] bookData = line.split(";");
            Book book = new Book(bookData[0], bookData[1], bookData[2], bookData[3], bookData[4]);
            books.add(book);
        }
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getById(int bookId) {
        return getAllBooks().get(bookId);
    }
}
