package com.zihler.library.adapters.file_persistence;

import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.entities.ReadingMode;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileBasedBookRepository {

    private List<Book> books;

    public FileBasedBookRepository(ResourceLoader resourceLoader) {

        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            resourceLoader.getResource("classpath:books.csv").getInputStream(),
                            StandardCharsets.UTF_8
                    )
            );
            books = new ArrayList<>();
            while (bufferedReader.ready()) {
                final String line = bufferedReader.readLine();
                final String[] bookAsStings = line.split(";");
                Book book = new Book(Integer.parseInt(bookAsStings[0]), bookAsStings[1], bookAsStings[2], ReadingMode.valueOf(bookAsStings[3]), bookAsStings[4]);
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book findById(int id) {
        return books.get(id);
    }
}
