package com.zihler.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin
@RestController
@RequestMapping("api/library")
public class Library {
    private final FileBasedInMemoryBookRepository bookRepository;
    private final RentalFactory rentalFactory;
    private InMemoryCustomerRepository customerRepository;

    @Autowired
    public Library(ResourceLoader resourceLoader) throws IOException {
        this.customerRepository = new InMemoryCustomerRepository();
        this.bookRepository = new FileBasedInMemoryBookRepository(resourceLoader);
        rentalFactory = new RentalFactory(bookRepository);
    }

    @GetMapping(
            value = "/books",
            produces = APPLICATION_JSON_UTF8_VALUE
    )
    public List<String[]> getBooks() {
        return bookRepository.getAll().stream()
                .map(book -> new String[]{
                        book.getKey() + "",
                        book.getTitle(),
                        book.getAuthors(),
                        book.getReadingMode(),
                        book.getLink()
                })
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/fee", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> calculateFee(@RequestBody List<String> rentalRequests) {
        if (rentalRequests == null || rentalRequests.size() == 0) {
            throw new IllegalArgumentException("rental requests cannot be null!");
        }
        String customerName = rentalRequests.remove(0);
        StringRentalRecordPresenter stringRentalRecordPresenter = new StringRentalRecordPresenter();

        RentBooksInteractor rentBooksInteractor = new RentBooksInteractor(customerRepository, rentalFactory, stringRentalRecordPresenter);

        RentalsRequest rentalsRequest = new RentalsRequest(customerName, rentalRequests);
        rentBooksInteractor.rent(rentalsRequest);

        return stringRentalRecordPresenter.getRentalsResponse();
    }

}

