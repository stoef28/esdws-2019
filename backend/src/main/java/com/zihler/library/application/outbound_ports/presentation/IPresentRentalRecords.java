package com.zihler.library.application.outbound_ports.presentation;

import com.zihler.library.domain.values.RentalRecordDocument;

public interface IPresentRentalRecords {
    void present(RentalRecordDocument rentalRecordDocument);
}
