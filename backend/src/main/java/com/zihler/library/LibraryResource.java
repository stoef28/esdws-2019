package com.zihler.library;

import com.zihler.library.adapters.file_persistence.FileBasedBookRepository;
import com.zihler.library.adapters.rest.RestRentalRecordPresenter;
import com.zihler.library.application.use_cases.rent_books.RentBooks;
import com.zihler.library.application.use_cases.rent_books.ports.RentBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin
@RestController
@RequestMapping("api/library")
public class LibraryResource {
    private final RestRentalRecordPresenter restRentalRecordPresenter = new RestRentalRecordPresenter();
    private final RentBooks rentBooks;
    private ResourceLoader resourceLoader;

    @Autowired
    public LibraryResource(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.rentBooks = new RentBooks(resourceLoader);
    }

    @GetMapping(
            value = "/books",
            produces = APPLICATION_JSON_UTF8_VALUE
    )
    public List<String[]> getBooks() throws IOException {
        final List<String[]> books = new ArrayList<>();
        final BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        resourceLoader.getResource("classpath:books.csv").getInputStream(),
                        StandardCharsets.UTF_8
                )
        );
        while (bufferedReader.ready()) {
            final String line = bufferedReader.readLine();
            final String[] book = line.split(";");
            books.add(book);
        }
        return books;
    }

    @PostMapping(value = "/fee", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String> calculateFee(@RequestBody List<String> rentBooksRequests) throws IOException {
        if (rentBooksRequests == null || rentBooksRequests.size() == 0) {
            throw new IllegalArgumentException("rent books requests cannot be null!");
        }
        String customerName = rentBooksRequests.remove(0);
        // fetch customer

        List<RentBookRequest> rentBookRequests = getRentBookRequests(rentBooksRequests);


        return rentBooks.executeWith(customerName, rentBookRequests, restRentalRecordPresenter);
    }

    private List<RentBookRequest> getRentBookRequests(@RequestBody List<String> rentBooksRequests) {
        List<RentBookRequest> rentBookRequests = new ArrayList<>();
        for (String rentBooksRequest : rentBooksRequests) {
            final String[] rentalAsString = rentBooksRequest.split(" ");
            final RentBookRequest rentBookRequest = new RentBookRequest(
                    Integer.parseInt(rentalAsString[0]),
                    Integer.parseInt(rentalAsString[1]));
            rentBookRequests.add(rentBookRequest);
        }
        return rentBookRequests;
    }

}

