package com.example.trolley;

import android.graphics.Bitmap;

public class Item {
    private String name;
    private long barcode;
    private double colesPrice;
    private double colesWasPrice;
    private boolean colesHasCupPrice;
    private String colesCupPrice;
    private double woolworthsPrice;
    private double woolworthsWasPrice;
    private String woolworthsCupPrice;
    private boolean woolworthsHasCupPrice;
    private boolean atColes;
    private boolean atWoolworths;
    private boolean isInStock;
    private String woolworthsImageURL;
    private String colesImageURL;
    private Bitmap colesImage;
    private Bitmap wooliesImage;
    private String colesCode;
    private String brand;
    private boolean colesDone;
    private boolean woolworthsDone;
    private boolean onColesList;
    private boolean onWooliesList;

    // Constructor
    public Item(String name) {
        this.name = name;
        this.barcode = 6969;
        this.colesPrice = -1;
        this.colesWasPrice = 0;
        this.colesHasCupPrice = false;
        this.colesCupPrice = "";
        this.woolworthsPrice = -1;
        this.woolworthsHasCupPrice = false;
        this.woolworthsCupPrice = "";
        this.isInStock = true;
        this.atWoolworths = false;
        this.atColes = false;
        this.colesCode = "0";
        this.colesDone = false;
        this.woolworthsDone = false;
        this.onColesList = false;
        this.onWooliesList = false;
    }

    public Item() {
        this.name = "default name";
        this.barcode = 6969;
        this.colesPrice = -1;
        this.colesWasPrice = 0;
        this.colesHasCupPrice = false;
        this.colesCupPrice = "";
        this.woolworthsPrice = -1;
        this.woolworthsHasCupPrice = false;
        this.woolworthsCupPrice = "";
        this.isInStock = true;
        this.atWoolworths = false;
        this.atColes = false;
        this.colesCode = "0";
        this.colesDone = false;
        this.woolworthsDone = false;
        this.onColesList = false;
        this.onWooliesList = false;
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

    public double getColesWasPrice() {
        return colesWasPrice;
    }

    public double getWoolworthsPrice() {
        return woolworthsPrice;
    }

    public String getWoolworthsCupPrice() {
        return woolworthsCupPrice;
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
        return ((colesPrice < woolworthsPrice && colesPrice != -1) || woolworthsPrice == -1);
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

    public void setColesWasPrice(double price) {
        this.colesWasPrice = price;
    }

    public void setWoolworthsPrice(double price) {
        this.woolworthsPrice = price;
    }

    public void setWoolworthsCupPrice(String price) {
        this.woolworthsCupPrice = price;
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

    public String getColesCupPrice() {
        return colesCupPrice;
    }

    public void setColesCupPrice(String colesCupPrice) {
        this.colesCupPrice = colesCupPrice;
    }

    public boolean isWoolworthsHasCupPrice() {
        return woolworthsHasCupPrice;
    }

    public void setWoolworthsHasCupPrice(boolean woolworthsHasCupPrice) {
        this.woolworthsHasCupPrice = woolworthsHasCupPrice;
    }

    public boolean isColesHasCupPrice() {
        return colesHasCupPrice;
    }

    public void setColesHasCupPrice(boolean colesHasCupPrice) {
        this.colesHasCupPrice = colesHasCupPrice;
    }

    public double getWoolworthsWasPrice() {
        return woolworthsWasPrice;
    }

    public void setWoolworthsWasPrice(double woolworthsWasPrice) {
        this.woolworthsWasPrice = woolworthsWasPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isWoolworthsDone() {
        return woolworthsDone;
    }

    public void setWoolworthsDone(boolean woolworthsDone) {
        this.woolworthsDone = woolworthsDone;
    }

    public boolean isColesDone() {
        return colesDone;
    }

    public void setColesDone(boolean colesDone) {
        this.colesDone = colesDone;
    }

    public boolean isOnColesList() {
        return onColesList;
    }

    public void setOnColesList(boolean onColesList) {
        this.onColesList = onColesList;
    }

    public boolean isOnWooliesList() {
        return onWooliesList;
    }

    public void setOnWooliesList(boolean onWooliesList) {
        this.onWooliesList = onWooliesList;
    }
}
