package com.zihler.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin
@RestController
@RequestMapping("api/library")
public class Library {
    private final FileBasedInMemoryBookRepository bookRepository;
    private InMemoryCustomerRepository customerRepository;

    @Autowired
    public Library(ResourceLoader resourceLoader) throws IOException {
        this.customerRepository = new InMemoryCustomerRepository();
        this.bookRepository = new FileBasedInMemoryBookRepository(resourceLoader);
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

        Customer customer = customerRepository.findByUsername(customerName);

        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + customer.getName() + "\n";

        for (int i = 0; i < rentalRequests.size(); i++) {
            final String[] rentalData = rentalRequests.get(i).split(" ");
            int bookKey = Integer.parseInt(rentalData[0]);
            Rental rental = new Rental(
                    bookRepository.getByKey(bookKey),
                    Integer.parseInt(rentalData[1])
            );

            double thisAmount = rental.getAmount();

            // add frequent renter points
            frequentRenterPoints += rental.getFrequentRenterPoints();

            // create figures for this rental
            result += "\t'" + rental.getBookTitle() + "' by '" + rental.getBookAuthors() + "' for " + rental.getDaysRented() + " days: \t" + thisAmount + " $\n";
            totalAmount += thisAmount;
        }

        // add footer lines
        result += "You owe " + totalAmount + " $\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points\n";

        return List.of(result);
    }

}

