package com.example.Proxy;

public class RealImage implements Image {
    String fileName = "";
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading image: " + this.fileName);
    }

    @Override
    public void display() {
        System.out.println("Display image: " +this.fileName);
    }
}
