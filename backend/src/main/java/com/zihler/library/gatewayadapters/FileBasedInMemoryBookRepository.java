package com.zihler.library.gatewayadapters;

import com.zihler.library.entities.Book;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileBasedInMemoryBookRepository {
    private final List<Book> books;
    ResourceLoader resourceLoader;

    public FileBasedInMemoryBookRepository(ResourceLoader resourceLoader) throws IOException {
        this.resourceLoader = resourceLoader;
        final BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        this.resourceLoader.getResource("classpath:books.csv").getInputStream(),
                        StandardCharsets.UTF_8
                )
        );
        books = new ArrayList<>();
        while (bufferedReader.ready()) {
            String nextBook = bufferedReader.readLine();
            Book book = Book.from(nextBook);
            books.add(book);
        }
    }

    public List<Book> getAll() {
        return books;
    }

    public Book getByKey(int key) {
        return books.get(key);
    }
}
