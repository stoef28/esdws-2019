package com.zihler.library;

import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.values.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
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
        List<Book> books = new ArrayList<>();
        while (bufferedReader.ready()) {
            final String line = bufferedReader.readLine();
            final String[] bookData = line.split(";");
            BookId bookId = BookId.from(bookData[0]);
            Title title = Title.from(bookData[1]);
            Authors authors = Authors.from(List.of(bookData[2].split(",")).stream().map(Author::new).collect(toList()));
            ReadingMode readingMode = ReadingMode.valueOf(bookData[3]);
            ThumbnailLink thumbnailLink = ThumbnailLink.from(bookData[4]);
            Book book = new Book(bookId, title, authors, readingMode, thumbnailLink);
            books.add(book);
        }

        // calculate fee, frequent renter points, and document to display in front end
        Amount totalAmount = Amount.of(0);
        FrequentRenterPoints frequentRenterPoints = FrequentRenterPoints.of(0);
        String result = "Rental Record for " + customer.getName() + "\n";

        for (int i = 0; i < rentBooksRequests.size(); i++) {
            final String[] rentalData = rentBooksRequests.get(i).split(" ");

            BookId bookId = BookId.from(rentalData[0]);
            DaysRented daysRented = DaysRented.from(rentalData[1]);

            Book book = books.get(bookId.asInt());
            Rental rental = new Rental(book, daysRented);

            frequentRenterPoints.plus(rental.frequentRenterPoints());
            // create figures for this rental
            result += "\t'" + rental.bookTitle() + "' by '" + rental.bookAuthors() + "' for " + rental.daysRented() + " days: \t" + rental.amount() + " $\n";
            totalAmount.plus(rental.amount());
        }

        // add footer lines
        result += "You owe " + totalAmount + " $\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points\n";

        return List.of(result);
    }

}

