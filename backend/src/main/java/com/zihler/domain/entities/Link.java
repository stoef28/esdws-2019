package com.zihler.domain.entities;

public class Link {
    private String link;

    public Link(String link) {

        this.link = link;
    }

    public static Link from(String link) {
        return new Link(link);
    }
}
