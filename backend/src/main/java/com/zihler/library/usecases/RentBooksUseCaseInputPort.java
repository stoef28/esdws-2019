package com.zihler.library.usecases;

import java.util.List;

public interface RentBooksUseCaseInputPort {
    void rent(List<String> booksToRent, String username);
}
