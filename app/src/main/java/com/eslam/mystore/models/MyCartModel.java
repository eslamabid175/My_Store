package com.eslam.mystore.models;

import java.io.Serializable;

public class MyCartModel implements Serializable {
    String ProductName;
    int ProductPrice;
    String currentDate;
    String currentTime;
    String totlalQuantity;
    int totalPrice;

    public MyCartModel() {
    }

    public MyCartModel(String productName, int productPrice, String currentDate, String currentTime, String totlalQuantity, int totalPrice) {
        ProductName = productName;
        ProductPrice = productPrice;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.totlalQuantity = totlalQuantity;
        this.totalPrice = totalPrice;

    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        ProductPrice = productPrice;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getTotlalQuantity() {
        return totlalQuantity;
    }

    public void setTotlalQuantity(String totlalQuantity) {
        this.totlalQuantity = totlalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
