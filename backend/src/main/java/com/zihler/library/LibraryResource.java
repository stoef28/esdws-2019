package com.zihler.library;

import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.entities.ReadingMode;
import com.zihler.library.domain.values.Rental;
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
    private InMemoryCustomerRepository customerRepository;
    private ResourceLoader resourceLoader;

    @Autowired
    public LibraryResource(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.customerRepository = new InMemoryCustomerRepository();
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
        Customer customer = customerRepository.findByUsername(customerName);

        // fetch books
        final BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        resourceLoader.getResource("classpath:books.csv").getInputStream(),
                        StandardCharsets.UTF_8
                )
        );
        final List<Book> books = new ArrayList<>();
        while (bufferedReader.ready()) {
            final String line = bufferedReader.readLine();
            final String[] bookAsStings = line.split(";");
            Book book = new Book(Integer.parseInt(bookAsStings[0]), bookAsStings[1], bookAsStings[2],ReadingMode.valueOf(bookAsStings[3]), bookAsStings[4]);
            books.add(book);
        }

        // calculate fee, frequent renter points, and document to display in front end
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + customer.getName() + "\n";

        for (String rentBooksRequest : rentBooksRequests) {
            final String[] rentalAsString = rentBooksRequest.split(" ");
            final Rental rental = new Rental(
                    books.get(Integer.parseInt(rentalAsString[0])),
                    Integer.parseInt(rentalAsString[1]));

            frequentRenterPoints += rental.getFrequentRenterPoints();

            // create figures for this rental
            result += "\t'" + rental.getBookTitle() + "' by '" + rental.getBookAuthors() + "' for " + rental.getDaysRented() + " days: \t" + rental.getAmount() + " $\n";
            totalAmount += rental.getAmount();
        }

        // add footer lines
        result += "You owe " + totalAmount + " $\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points\n";

        return List.of(result);
    }

}

