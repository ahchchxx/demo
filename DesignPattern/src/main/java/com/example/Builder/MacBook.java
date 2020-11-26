package com.example.Builder;

public class MacBook extends Computer {
    protected MacBook() {
    }
 
    @Override
    public void setOs() {
        mOs="Mac OS X 12";
    }
}