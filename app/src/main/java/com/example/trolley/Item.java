package com.example.trolley;

public class Item {
    private String name;
    private long barcode;
    private double price;

    // Constructor
    public Item(String name, long barcode, double price) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public long getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }
}
