package com.zihler.adapters.rest;

import com.zihler.adapters.file_persistence.FileBasedBookRepository;
import com.zihler.adapters.in_memory_persistence.InMemoryCustomerRepository;
import com.zihler.application.outbound_ports.persistence.BookRepository;
import com.zihler.application.outbound_ports.persistence.CustomerRepository;
import com.zihler.application.use_cases.rent_books.RentBooksInteractor;
import com.zihler.application.use_cases.rent_books.ports.RentBooks;
import com.zihler.application.use_cases.rent_books.ports.RentalsRequest;
import com.zihler.domain.CustomerName;
import com.zihler.domain.RentalsDocument;
import com.zihler.library.StringRentalRecordPresenter;
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
public class LibraryResource {
    private final BookRepository bookRepository;
    private final RentBookInput rentalInput;
    private CustomerRepository customerRepository;

    @Autowired
    public LibraryResource(ResourceLoader resourceLoader) throws IOException {
        this.customerRepository = new InMemoryCustomerRepository();
        this.bookRepository = new FileBasedBookRepository(resourceLoader);
        rentalInput = new RentBookInput(bookRepository);
    }

    @GetMapping(
            value = "/books",
            produces = APPLICATION_JSON_UTF8_VALUE
    )
    public List<String[]> getBooks() {
        return bookRepository.getAll().stream()
                .map(book -> new String[]{
                        book.getKey().toString(),
                        book.getTitle().toString(),
                        book.getAuthors().toString(),
                        book.getReadingMode().toString(),
                        book.getLink().toString()
                })
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/fee", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> calculateFee(@RequestBody List<String> rentalRequests) {
        if (rentalRequests == null || rentalRequests.size() == 0) {
            throw new IllegalArgumentException("rental requests cannot be null!");
        }
        CustomerName customerName = CustomerName.from(rentalRequests.remove(0));
        List<RentalRequest> rentalRequests = RentalRequest.from(rentalRequests);

        RentalsDocument rentalsRequest = RentalsRequest.from(rentalRequests);
        RentBookInput rentBookInput = new RentBookInput(bookRepository);
        RentalRecordRestPresenter stringRentalRecordPresenter = new RentalRecordRestPresenter();

        RentalsRequest rentalsRequest = new RentalsRequest(customerName, rentalRequests);
        RentBooks rentBooks = new RentBooksInteractor(
                customerRepository,
                rentalInput,
                stringRentalRecordPresenter
        );

        rentBooks.execute(rentBookInput, stringRentalRecordPresenter);

        return stringRentalRecordPresenter.presentation();
    }

}

