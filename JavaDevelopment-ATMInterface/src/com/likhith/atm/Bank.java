package com.likhith.atm;

import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Bank() {
        // Default Accounts
        accounts.add(new Account("user1", "1234", "ACC1001", 0));
        accounts.add(new Account("user2", "4321", "ACC1002", 0));
    }

    // Login
    public Account login(String userId, String pin) {
        for (Account account : accounts) {
            if (account.getUserId().equals(userId)
                    && account.getPin().equals(pin)) {
                return account;
            }
        }
        return null;
    }

    // Deposit
    public void deposit(Account account, double amount) {
        account.deposit(amount);

        transactions.add(
                new Transaction(
                        "Deposit",
                        amount,
                        "Amount deposited successfully"));
    }

    // Withdraw
    public boolean withdraw(Account account, double amount) {
        if (account.withdraw(amount)) {
            transactions.add(
                    new Transaction(
                            "Withdraw",
                            amount,
                            "Cash Withdrawn"));

            return true;
        }

        return false;
    }

    // Transfer
    public boolean transfer(Account sender,
                            String receiverId,
                            double amount) {
        Account receiver = findAccount(receiverId);

        if (receiver == null) {
            return false;
        }

        if (sender.transfer(receiver, amount)) {
            transactions.add(
                    new Transaction(
                            "Transfer",
                            amount,
                            "Transferred to " + receiverId));

            return true;
        }

        return false;
    }

    // Find Receiver Account
    public Account findAccount(String userId) {
        for (Account account : accounts) {
            if (account.getUserId().equals(userId)) {
                return account;
            }
        }

        return null;
    }

    // Transaction History
    public void showTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("\nNo Transactions Found.");
            return;
        }

        System.out.println("\n==============================================================");
        System.out.println("                    TRANSACTION HISTORY");
        System.out.println("==============================================================");

        System.out.printf("%-20s %-12s %-15s %s%n",
                "DATE & TIME",
                "TYPE",
                "AMOUNT",
                "DESCRIPTION");

        System.out.println("--------------------------------------------------------------");

        for (Transaction t : transactions) {
            System.out.println(t);
        }

        System.out.println("==============================================================");
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}