package com.example.trolley;

public class Item {
    private String name;
    private long barcode;
    private double colesPrice;
    private double woolworthsPrice;
    private boolean atColes;
    private boolean atWoolworths;

    // Constructor
    public Item(String name) {
        this.name = name;
        this.barcode = 6969;
        this.colesPrice = 0;
        this.woolworthsPrice = 0;
    }

    public Item() {
        this.name = "default name";
        this.barcode = 6969;
        this.colesPrice = 0;
        this.woolworthsPrice = 0;
    }

    public String getName() {
        return name;
    }

    public long getBarcode() {
        return barcode;
    }

    public double getColesPrice() {
        return colesPrice;
    }

    public double getWoolworthsPrice() {
        return woolworthsPrice;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }
    public void setColesPrice(double price) {
        this.colesPrice = price;
    }
    public void setWoolworthsPrice(double price) {
        this.woolworthsPrice = price;
    }
}
