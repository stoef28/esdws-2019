package com.zihler.library.application.use_cases.rent_books.ports;

public class RentBookRequest{
        private int bookId;
        private int daysRented;

        public RentBookRequest(int bookId, int daysRented) {
                this.bookId = bookId;
                this.daysRented = daysRented;
        }

        public int getBookId() {
                return bookId;
        }

        public int getDaysRented() {
                return daysRented;
        }
}
