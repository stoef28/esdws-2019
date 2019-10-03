package com.zihler.library;

import com.zihler.library.adapters.file_persistance.FileBasedBookRepository;
import com.zihler.library.domain.entities.Book;
import com.zihler.library.domain.values.*;
import com.zihler.library.domain.values.RentBookRequest;
import com.zihler.library.domain.values.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.*;
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
        String customerName = rentBooksRequests.remove(0);

        // fetch customer
        Customer customer = customerRepository.findByUsername(customerName);

        // calculate fee, frequent renter points, and document to display in front end
        List<Rental> rentals = new ArrayList<>();
        for (int i = 0; i < rentBooksRequests.size(); i++) {
            final String[] rentalData = rentBooksRequests.get(i).split(" ");

            BookId bookId = BookId.from(rentalData[0]);
            DaysRented daysRented = DaysRented.from(rentalData[1]);

            Book book = bookRepository.findById(bookId);
            Rental rental = new Rental(book, daysRented);
            RentBookRequest rentBookRequest = new RentBookRequest(Integer.parseInt(rentalData[0]), Integer.parseInt(rentalData[1]));

            Book book = bookRepository.findById(rentBookRequest.getBookId());
            Rental rental = new Rental(book, rentBookRequest.getDaysRented());
            rentals.add(rental);
        }

        String result = "Rental Record for " + customer.getName() + "\n";
        result += format(rentals);
        // add footer lines
        result += "You owe " + getTotalAmount(rentals) + " $\n";
        result += "You earned " + getFrequentRenterPoints(rentals) + " frequent renter points\n";

        return List.of(result);
    }

    private Amount getTotalAmount(List<Rental> rentals) {
        Amount totalAmount = Amount.of(0);
        for (Rental rental : rentals) {
            totalAmount.plus(rental.amount());
        }
        return totalAmount;
    }

    private FrequentRenterPoints getFrequentRenterPoints(List<Rental> rentals) {
        FrequentRenterPoints frequentRenterPoints = FrequentRenterPoints.of(0);

        for (Rental rental : rentals) {
            frequentRenterPoints.plus(rental.frequentRenterPoints());
        }

        return frequentRenterPoints;
    }

    private String format(List<Rental> rentals) {
        String result="";
        for (Rental rental : rentals) {
            // create figures for this rental
            result += "\t'" + rental.bookTitle() + "' by '" + rental.bookAuthors() + "' for " + rental.daysRented() + " days: \t" + rental.amount() + " $\n";
        }
        return result;
    }

}

