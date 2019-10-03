package com.zihler.library.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Authors {
    private List<Author> authors;

    public Authors(List<Author> authors) {
        this.authors = authors;
    }

    public static Authors from(List<Author> authors) {
        return new Authors(authors);
    }

    @Override
    public String toString() {
        return authors.stream()
                .map(Author::toString)
                .collect(Collectors.joining(","));
    }
}
