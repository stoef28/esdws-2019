package com.zihler.library;

import com.zihler.library.adapters.file_persistence.FileBasedBookRepository;
import com.zihler.library.application.use_cases.rent_books.ports.RentBookRequest;
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

        List<RentBookRequest> rentBookRequests = getRentBookRequests(rentBooksRequests);
        List<Rental> rentals = getRentals(rentBookRequests);

        String result = "Rental Record for " + customer.getName() + "\n";
        result += format(rentals);
        // add footer lines
        result += "You owe " + getTotalAmount(rentals) + " $\n";
        result += "You earned " + getFrequentRenterPoints(rentals) + " frequent renter points\n";

        return List.of(result);
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

    private double getTotalAmount(List<Rental> rentals) {
        double totalAmount = 0;
        for (Rental rental : rentals) {
            totalAmount += rental.getAmount();
        }
        return totalAmount;
    }

    private String format(List<Rental> rentals) {
        StringBuilder result = new StringBuilder();
        for (Rental rental : rentals) {
            // create figures for this rental
            result.append("\t'")
                    .append(rental.getBookTitle())
                    .append("' by '")
                    .append(rental.getBookAuthors())
                    .append("' for ")
                    .append(rental.getDaysRented())
                    .append(" days: \t")
                    .append(rental.getAmount())
                    .append(" $\n");
        }
        return result.toString();
    }

    private int getFrequentRenterPoints(List<Rental> rentals) {
        int frequentRenterPoints = 0;
        for (Rental rental : rentals) {
            frequentRenterPoints += rental.getFrequentRenterPoints();
        }
        return frequentRenterPoints;
    }

    private List<Rental> getRentals(@RequestBody List<RentBookRequest> rentBooksRequests) {
        List<Rental> rentals = new ArrayList<>();
        for (RentBookRequest rentBookRequest : rentBooksRequests) {
            final Rental rental = new Rental(
                    fileBasedBookRepository.findById(rentBookRequest.getBookId()),
                    rentBookRequest.getDaysRented());
            rentals.add(rental);
        }
        return rentals;
    }

}

