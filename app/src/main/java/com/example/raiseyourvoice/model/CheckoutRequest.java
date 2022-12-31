package com.example.raiseyourvoice.model;

public class CheckoutRequest {
    private String number;

    public CheckoutRequest(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
