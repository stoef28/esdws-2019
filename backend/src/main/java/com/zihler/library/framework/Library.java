package com.zihler.library.framework;

import com.zihler.library.gateways.BookRepository;
import com.zihler.library.gateways.CustomerRepository;
import com.zihler.library.presenters.StringRentalReceiptReceiptPresenter;
import com.zihler.library.usecases.GatherBooksUseCaseInputPortAdapter;
import com.zihler.library.usecases.RentBooksUseCaseInputPortAdapter;
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
        return new GatherBooksUseCaseInputPortAdapter(bookRepository).getBooks();
    }

    @PostMapping(value = "/fee", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> calculateFee(@RequestBody List<String> booksToRent) {
        if (booksToRent == null || booksToRent.size() == 0) {
            throw new IllegalArgumentException("rental requests cannot be null!");
        }
        String username = booksToRent.remove(0);

        StringRentalReceiptReceiptPresenter stringRentalReceiptPresenter = new StringRentalReceiptReceiptPresenter();
        RentBooksUseCaseInputPortAdapter rentBooks = new RentBooksUseCaseInputPortAdapter(customerRepository, bookRepository, stringRentalReceiptPresenter);
        rentBooks.rent(booksToRent, username);
        String receiptForView = stringRentalReceiptPresenter.formatRentalReceipt();
        return List.of(receiptForView);
    }
}

