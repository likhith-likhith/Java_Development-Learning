package com.likhith.atm.gui;

import com.likhith.atm.Account;
import com.likhith.atm.Bank;

import javax.swing.*;
import java.awt.*;

public class WithdrawFrame extends JFrame {

    private Bank bank;
    private Account account;
    private DashboardFrame dashboard;

    private JTextField amountField;

    public WithdrawFrame(Bank bank, Account account, DashboardFrame dashboard) {

        this.bank = bank;
        this.account = account;
        this.dashboard = dashboard;

        setTitle("Withdraw Money");
        setSize(420,260);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245,248,250));

        JLabel title = new JLabel("Withdraw Money");
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setForeground(new Color(0,102,204));
        title.setBounds(95,20,220,35);

        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setBounds(50,90,80,25);

        amountField = new JTextField();
        amountField.setBounds(140,90,200,30);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(50,170,120,35);

        JButton backButton = new JButton("Back");
        backButton.setBounds(220,170,120,35);

        panel.add(title);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(backButton);

        add(panel);

        withdrawButton.addActionListener(e -> withdraw());

        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void withdraw() {

        String text = amountField.getText().trim();

        if(text.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Amount");

            return;
        }

        double amount;

        try{

            amount = Double.parseDouble(text);

        }catch(NumberFormatException ex){

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Amount");

            return;
        }

        if(amount <= 0){

            JOptionPane.showMessageDialog(
                    this,
                    "Amount must be greater than zero");

            return;
        }

        if(bank.withdraw(account, amount)){

            dashboard.refreshBalance();

            JOptionPane.showMessageDialog(
                    this,
                    "₹" + amount + " Withdrawn Successfully");

            dispose();

        }else{

            JOptionPane.showMessageDialog(
                    this,
                    "Insufficient Funds!");

        }

    }

}