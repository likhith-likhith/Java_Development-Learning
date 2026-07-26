package com.likhith.atm;

public class Account {

    private String userId;
    private String pin;
    private String accountNumber;
    private double balance;

    public Account(String userId, String pin, String accountNumber, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit Money
    public void deposit(double amount) {
        balance += amount;
    }

    // Withdraw Money
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }

        return false;
    }

    // Transfer Money
    public boolean transfer(Account receiver, double amount) {
        if (amount <= balance) {
            balance -= amount;
            receiver.deposit(amount);

            return true;
        }

        return false;
    }
}