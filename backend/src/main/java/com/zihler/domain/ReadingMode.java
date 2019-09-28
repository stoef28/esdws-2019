package com.zihler.domain;

public enum ReadingMode {
    IMAGE, TEXT, BOTH;


    @Override
    public String toString() {
        return name();
    }
}
