package com.zihler.library.entities;

public class RentalReceipt {
    private RentalRecord rentalRecord;

    public RentalReceipt(RentalRecord rentalRecord) {
        this.rentalRecord = rentalRecord;
    }

    public static RentalReceipt of(RentalRecord rentalRecord) {
        return new RentalReceipt(rentalRecord);
    }

    public RentalRecord getRentalRecord() {
        return rentalRecord;
    }
}
