package com.zihler.adapters.rest;

import com.zihler.adapters.file_persistence.FileBasedBookRepository;
import com.zihler.adapters.in_memory_persistence.InMemoryCustomerRepository;
import com.zihler.application.outbound_ports.persistence.BookRepository;
import com.zihler.application.outbound_ports.persistence.CustomerRepository;
import com.zihler.application.use_cases.rent_books.RentBooksInteractor;
import com.zihler.application.use_cases.rent_books.ports.RentBooks;
import com.zihler.application.use_cases.rent_books.ports.RentBooksInput;
import com.zihler.application.use_cases.rent_books.ports.RentalRequest;
import com.zihler.application.use_cases.rent_books.ports.RentalsRequest;
import com.zihler.domain.CustomerName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin
@RestController
@RequestMapping("api/library")
public class LibraryResource {
    private final BookRepository bookRepository;
    private CustomerRepository customerRepository;

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
        return bookRepository.getAll().stream()
                .map(book -> new String[]{
                        book.key() + "",
                        book.title().toString(),
                        book.authors().toString(),
                        book.readingMode().name(),
                        book.link().toString()
                })
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/fee", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> calculateFee(@RequestBody List<String> rentalRequestData) {
        if (rentalRequestData == null || rentalRequestData.size() == 0) {
            throw new IllegalArgumentException("rental requests cannot be null!");
        }
        CustomerName customerName = CustomerName.from(rentalRequestData.remove(0));
        List<RentalRequest> rentalRequests = RentalRequests.from(rentalRequestData);
        RentalsRequest rentalsRequest = new RentalsRequest(customerName, rentalRequests);

        RentBooksInput input = new RentBooksInput(bookRepository, rentalsRequest);
        RentalRecordRestPresenter presenter = new RentalRecordRestPresenter();

        RentBooks rentBooks = new RentBooksInteractor(customerRepository);

        rentBooks.executeWith(input, presenter);

        return presenter.presentation();
    }

}

