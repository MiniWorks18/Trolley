package com.example.trolley;

public class Item {
    private String name;
    private long barcode;
    private double colesPrice;
    private double woolworthsPrice;
    private boolean atColes;
    private boolean atWoolworths;
    private boolean isInStock;

    // Constructor
    public Item(String name) {
        this.name = name;
        this.barcode = 6969;
        this.colesPrice = 0;
        this.woolworthsPrice = 0;
        this.isInStock = true;
        this.atWoolworths = false;
        this.atColes = false;
    }

    public Item() {
        this.name = "default name";
        this.barcode = 6969;
        this.colesPrice = 0;
        this.woolworthsPrice = 0;
        this.isInStock = true;
        this.atWoolworths = false;
        this.atColes = false;
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
    public boolean getIsInStock() {
        return isInStock;
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
    public void setInStock(boolean isInStock) {
        this.isInStock = isInStock;
    }
    public void setAtColes(boolean atColes) {
        this.atColes = atColes;
    }
    public void setAtWoolworths(boolean atWoolworths) {
        this.atWoolworths = atWoolworths;
    }
}
