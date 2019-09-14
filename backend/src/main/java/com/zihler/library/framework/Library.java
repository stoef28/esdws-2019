package com.zihler.library.framework;

import com.zihler.library.gateways.BookRepository;
import com.zihler.library.gateways.CustomerRepository;
import com.zihler.library.presenters.RestRentalRecordPresenter;
import com.zihler.library.usecases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin
@RestController
@RequestMapping("api/library")
public class Library {
    private BookRepository bookRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public Library(BookRepository bookRepository,
                   CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping(
            value = "/books",
            produces = APPLICATION_JSON_UTF8_VALUE
    )
    public List<String[]> getBooks() {
        GatherBooksUseCaseInputPort gatherBooks = new GatherBooksUseCaseInputPortAdapter(bookRepository);
        StringArrayBooksPresenter presenter = new StringArrayBooksPresenter();
        gatherBooks.gatherAllBooks(presenter);
        return presenter.formatBooksForViewModel();
    }

    @PostMapping(value = "/fee", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> calculateFee(@RequestBody List<String> booksToRent) {
        if (booksToRent == null || booksToRent.size() == 0) {
            throw new IllegalArgumentException("rental requests cannot be null!");
        }
        String username = booksToRent.remove(0);
        RestRentalRecordPresenter presenter = new RestRentalRecordPresenter();
        RentBooksUseCaseInputPort rentBooks = new RentBooksUseCaseInputPortAdapter(customerRepository, bookRepository, presenter);
        RentalRequest rentals = RentalRequest.from(booksToRent, username);
        rentBooks.rent(rentals);
        return presenter.formatRentalReceiptForViewModel();
    }
}

