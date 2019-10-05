package com.zihler.library.adapters.rest;

import com.zihler.library.adapters.file_persistance.InMemoryCustomerRepository;
import com.zihler.library.adapters.file_persistance.FileBasedBookRepository;
import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.values.BookId;
import com.zihler.library.domain.values.CustomerName;
import com.zihler.library.domain.values.DaysRented;
import com.zihler.library.application.use_cases.rent_books.RentBooks;
import com.zihler.library.application.use_cases.rent_books.ports.RentBookRequest;
import com.zihler.library.application.use_cases.rent_books.ports.RentBooksInput;
import com.zihler.library.application.use_cases.rent_books.ports.RentBooksRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
        for (Book book : bookRepository.allBooks()) {
            books.add(new String[]{
                    book.id().toString(),
                    book.title().toString(),
                    book.authors().toString(),
                    book.readingMode().toString(),
                    book.thumbnailLink().toString()
            });
        }
        return books;
    }

    @PostMapping(value = "/fee", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> calculateFee(@RequestBody List<String> rentBooksRequests) {
        if (rentBooksRequests == null || rentBooksRequests.size() == 0) {
            throw new IllegalArgumentException("rent books requests cannot be null!");
        }

        CustomerName customerName = CustomerName.from(rentBooksRequests.remove(0));
        List<RentBookRequest> rentBookRequests = toRequests(rentBooksRequests);
        RentBooksRequest rentBooksRequest = RentBooksRequest.from(customerName, rentBookRequests);
        RentBooksInput rentBooksInput = new RentBooksInput(bookRepository, rentBooksRequest);

        RestRentalRecordPresenter restRentalRecordPresenter = new RestRentalRecordPresenter();
        RentBooks rentBooks = new RentBooks(customerRepository);
        rentBooks.with(rentBooksInput, restRentalRecordPresenter);

        return restRentalRecordPresenter.presentation();
    }

    private List<RentBookRequest> toRequests(List<String> rentBooksRequests) {
        List<RentBookRequest> rentBookRequests = new ArrayList<>();
        for (int i = 0; i < rentBooksRequests.size(); i++) {
            final String[] rentalData = rentBooksRequests.get(i).split(" ");

            BookId bookId = BookId.from(rentalData[0]);
            DaysRented daysRented = DaysRented.from(rentalData[1]);

            RentBookRequest rentBookRequest = RentBookRequest.from(bookId, daysRented);
            rentBookRequests.add(rentBookRequest);
        }
        return rentBookRequests;
    }


}

