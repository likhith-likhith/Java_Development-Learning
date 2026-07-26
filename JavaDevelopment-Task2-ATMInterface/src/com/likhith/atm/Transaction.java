package com.likhith.atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private double amount;
    private String description;
    private String dateTime;

    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        this.dateTime = LocalDateTime.now().format(formatter);
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "------------------------------------------\n"
                + "Date : " + dateTime + "\n"
                + "Type : " + type + "\n"
                + "Amount : ₹" + amount + "\n"
                + "Details : " + description + "\n"
                + "------------------------------------------";
    }
}