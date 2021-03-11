package com.example.Proxy;

public class ProxyImage implements Image {
    RealImage realImage;
    String fileName;
    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(this.fileName);
        }

        System.out.println("↓ before displaying");
        realImage.display();
        System.out.println("↑ after displaying");
    }
}
