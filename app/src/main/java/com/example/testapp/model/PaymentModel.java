package com.example.testapp.model;

import java.io.Serializable;

public class PaymentModel implements Serializable {
    private String productName;
    private String amount;
    private String number;
    private String image;

    public PaymentModel() {
    }

    public PaymentModel(String productName, String amount, String number, String image) {
        this.productName = productName;
        this.amount = amount;
        this.number = number;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
