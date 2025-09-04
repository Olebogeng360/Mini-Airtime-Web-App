package com.airtimeadvance;

public class AirtimeRequest {
    private String number;
    private double amount;

    public AirtimeRequest() {}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
       this.number = number;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}