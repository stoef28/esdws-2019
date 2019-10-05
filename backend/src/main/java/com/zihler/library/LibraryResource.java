package com.zihler.library;

import com.zihler.library.adapters.file_persistance.FileBasedBookRepository;
import com.zihler.library.adapters.rest.RestRentalRecordPresenter;
import com.zihler.library.domain.entities.Book;
import com.zihler.library.use_cases.rent_books.RentBooks;
import com.zihler.library.use_cases.rent_books.ports.RentBooksInput;
import com.zihler.library.use_cases.rent_books.ports.RentBookRequest;
import com.zihler.library.use_cases.rent_books.ports.RentBooksRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin
@RestController
@RequestMapping("api/library")
public class LibraryResource {
    private FileBasedBookRepository bookRepository;
    private InMemoryCustomerRepository customerRepository;

    @Autowired
    public LibraryResource(ResourceLoader resourceLoader) throws IOException {
        this.customerRepository = new InMemoryCustomerRepository();
        this.bookRepository = new FileBasedBookRepository(resourceLoader);
    }

    @GetMapping(
            value = "/books",
            produces = APPLICATION_JSON_UTF8_VALUE
    )
    public List<String[]> getBooks() {

        final List<String[]> books = new ArrayList<>();
        for (Book book : bookRepository.getAllBooks()) {
            books.add(new String[]{
                    String.valueOf(book.getId()),
                    book.getTitle(),
                    book.getAuthors(),
                    book.getReadingMode(),
                    book.getThumbnailLink()
            });
        }
        return books;
    }

    @PostMapping(value = "/fee", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> calculateFee(@RequestBody List<String> rentBooksRequests) {
        if (rentBooksRequests == null || rentBooksRequests.size() == 0) {
            throw new IllegalArgumentException("rent books requests cannot be null!");
        }
        String customerName = rentBooksRequests.remove(0);
        List<RentBookRequest> rentBookRequests = getRentBookRequests(rentBooksRequests);
        RentBooksRequest rentBooksRequest = RentBooksRequest.from(customerName, rentBookRequests);
        RentBooksInput rentBooksInput = new RentBooksInput(bookRepository, rentBooksRequest);

        RestRentalRecordPresenter restRentalRecordPresenter = new RestRentalRecordPresenter();
        RentBooks rentBooks = new RentBooks(customerRepository);
        rentBooks.executeWith(rentBooksInput, restRentalRecordPresenter);

        return restRentalRecordPresenter.presentation();
    }

    private List<RentBookRequest> getRentBookRequests(List<String> rentBooksRequests) {
        List<RentBookRequest> rentBookRequests = new ArrayList<>();
        for (int i = 0; i < rentBooksRequests.size(); i++) {
            final String[] rentalData = rentBooksRequests.get(i).split(" ");
            RentBookRequest rentBookRequest = new RentBookRequest(Integer.parseInt(rentalData[0]), Integer.parseInt(rentalData[1]));
            rentBookRequests.add(rentBookRequest);
        }
        return rentBookRequests;
    }

}

