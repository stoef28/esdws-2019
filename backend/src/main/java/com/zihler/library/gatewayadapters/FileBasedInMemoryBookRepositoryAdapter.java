package com.zihler.library.gatewayadapters;

import com.zihler.library.entities.Book;
import com.zihler.library.gateways.BookRepository;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class FileBasedInMemoryBookRepositoryAdapter implements BookRepository {

    FileBasedInMemoryBookRepository fileBasedInMemoryBookRepository;

    public FileBasedInMemoryBookRepositoryAdapter(ResourceLoader resourceLoader) {
        try {
            this.fileBasedInMemoryBookRepository = new FileBasedInMemoryBookRepository(resourceLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAll() {
        return this.fileBasedInMemoryBookRepository.getAll();
    }

    @Override
    public Book getByKey(int key) {
        return fileBasedInMemoryBookRepository.getByKey(key);
    }

}
