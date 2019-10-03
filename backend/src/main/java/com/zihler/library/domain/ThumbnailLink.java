package com.zihler.library.domain;

public class ThumbnailLink {
    private String thumbnailLink;

    private ThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    public static ThumbnailLink from(String thumbnailLink) {
        return new ThumbnailLink(thumbnailLink);
    }
}
