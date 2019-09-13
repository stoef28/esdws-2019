package com.zihler.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@CrossOrigin
@RestController
@RequestMapping("api/library")
public class Library {

    ResourceLoader resourceLoader;

    @Autowired
    public Library(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;

    }

    @GetMapping(value = "/books", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<String[]> getBooks() throws IOException {
        final List<String[]> books = new ArrayList<>();
        final BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        resourceLoader.getResource("classpath:books.csv").getInputStream()
                )
        );
        while (bufferedReader.ready()) {
            final String line = bufferedReader.readLine();
            final String[] book = line.split(";");
            books.add(book);
        }
        return books;
    }

    @PostMapping("/fee")
    public List<String> calculateFee(@RequestBody List<String> rentalRequests) throws IOException {
        String customerName = rentalRequests.remove(0);

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceLoader.getResource("classpath:books.csv").getInputStream()));
        final List<String[]> books = new ArrayList<>();
        while (bufferedReader.ready()) {
            final String line = bufferedReader.readLine();
            final String[] book = line.split(";");
            books.add(book);
        }

        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + customerName + "\n";

        for (int i = 0; i < rentalRequests.size(); i++) {
            final String[] rental = rentalRequests.get(i).split(" ");
            final String[] book = books.get(Integer.parseInt(rental[0]));
            double thisAmount = 0;

            int daysRented = Integer.parseInt(rental[1]);
            String readingMode =  book[3];
            switch (readingMode) {
                case "IMAGE":
                    thisAmount += 2;
                    if (daysRented > 2)
                        thisAmount += (daysRented - 2) * 1.5;
                    break;
                case "TEXT":
                    thisAmount += 1.5;
                    if (daysRented > 3)
                        thisAmount += (daysRented - 3) * 1.5;
                    break;
                case "BOTH":
                    thisAmount += daysRented * 3;
                    break;
            }

            // add frequent renter points
            frequentRenterPoints++;

            // add bonus for a reading mode "both"
            if (readingMode.equals("BOTH") && daysRented > 1) {
                frequentRenterPoints++;
            }

            // create figures for this rental
            result += "\t'" + book[1] + "' by '" + book[2] + "' for " + daysRented + " days: \t" + thisAmount + " $\n";
            totalAmount += thisAmount;
        }

        // add footer lines
        result += "You owe " + totalAmount + " $\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points\n";

        return List.of(result);
    }
}

