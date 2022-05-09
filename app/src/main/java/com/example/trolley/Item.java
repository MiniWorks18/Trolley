package com.example.trolley;

import android.graphics.Bitmap;

public class Item {
    private String name;
    private long barcode;
    private double colesPrice;
    private double woolworthsPrice;
    private boolean atColes;
    private boolean atWoolworths;
    private boolean isInStock;
    private String woolworthsImageURL;
    private String colesImageURL;
    private Bitmap colesImage;
    private Bitmap wooliesImage;
    private String colesCode;

    // Constructor
    public Item(String name) {
        this.name = name;
        this.barcode = 6969;
        this.colesPrice = 0;
        this.woolworthsPrice = 0;
        this.isInStock = true;
        this.atWoolworths = false;
        this.atColes = false;
        this.colesCode = "0";
    }

    public Item() {
        this.name = "default name";
        this.barcode = 6969;
        this.colesPrice = 0;
        this.woolworthsPrice = 0;
        this.isInStock = true;
        this.atWoolworths = false;
        this.atColes = false;
        this.colesCode = "0";
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

    public String getWoolworthsImageURL() {
        return woolworthsImageURL;
    }

    public String getColesImageURL() {
        return colesImageURL;
    }

    public Bitmap getColesImage() {
        return colesImage;
    }

    public Bitmap getWooliesImage() {
        return wooliesImage;
    }

    public String getColesCode() {
        return colesCode;
    }

    public boolean getIsAtWoolworths() {
        return atWoolworths;
    }

    public boolean getIsAtColes() {
        return atColes;
    }

    public boolean isColesCheaper() {
        return ((colesPrice < woolworthsPrice && colesPrice != 0) || woolworthsPrice == 0);
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

    public void setWoolworthsImageURL(String url) {
        this.woolworthsImageURL = url;
    }

    public void setColesImageURL(String url) {
        this.colesImageURL = url;
    }

    public void setColesImage(Bitmap colesImage) {
        this.colesImage = colesImage;
    }

    public void setWooliesImage(Bitmap wooliesImage) {
        this.wooliesImage = wooliesImage;
    }

    public void setColesCode(String colesCode) {
        this.colesCode = colesCode;
    }

}
