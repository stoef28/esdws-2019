package com.zihler.library;

public class RentalFactory {
    private FileBasedInMemoryBookRepository bookRepository;

    public RentalFactory(FileBasedInMemoryBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    Rental createRentalFrom(String nextRequest) {
        final String[] rentalData = nextRequest.split(" ");
        int bookKey = Integer.parseInt(rentalData[0]);
        return new Rental(
                bookRepository.getByKey(bookKey),
                Integer.parseInt(rentalData[1])
        );
    }
}
