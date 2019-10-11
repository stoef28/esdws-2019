package com.zihler.library;

import com.zihler.library.adapters.file_persistence.FileBasedBookRepository;
import com.zihler.library.domain.entities.Book;
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
    private final FileBasedBookRepository fileBasedBookRepository;
    private InMemoryCustomerRepository customerRepository;
    private ResourceLoader resourceLoader;

    @Autowired
    public LibraryResource(ResourceLoader resourceLoader) {
        this.fileBasedBookRepository = new FileBasedBookRepository(resourceLoader);
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

        List<Rental> rentals = getRentals(rentBooksRequests);

        int frequentRenterPoints = getFrequentRenterPoints(rentals);
        String result = format(customer, rentals);
        double totalAmount = getTotalAmount(rentals);

        // add footer lines
        result += "You owe " + totalAmount + " $\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points\n";

        return List.of(result);
    }

    private double getTotalAmount(List<Rental> rentals) {
        double totalAmount = 0;
        for (Rental rental : rentals) {
            totalAmount += rental.getAmount();
        }
        return totalAmount;
    }

    private String format(Customer customer, List<Rental> rentals) {
        String result = "Rental Record for " + customer.getName() + "\n";
        for (Rental rental : rentals) {
            // create figures for this rental
            result += "\t'" + rental.getBookTitle() + "' by '" + rental.getBookAuthors() + "' for " + rental.getDaysRented() + " days: \t" + rental.getAmount() + " $\n";
        }
        return result;
    }

    private int getFrequentRenterPoints(List<Rental> rentals) {
        int frequentRenterPoints = 0;
        for (Rental rental : rentals) {
            frequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

    private List<Rental> getRentals(@RequestBody List<String> rentBooksRequests) {
        List<Rental> rentals = new ArrayList<>();
        for (String rentBooksRequest : rentBooksRequests) {
            final String[] rentalAsString = rentBooksRequest.split(" ");
            final Rental rental = new Rental(
                    fileBasedBookRepository.findById(Integer.parseInt(rentalAsString[0])),
                    Integer.parseInt(rentalAsString[1]));
            rentals.add(rental);
        }
        return rentals;
    }

}

