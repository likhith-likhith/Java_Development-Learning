package com.likhith.atm.gui;

import com.likhith.atm.Account;
import com.likhith.atm.Bank;

import javax.swing.*;
import java.awt.*;

public class TransferFrame extends JFrame {

    private Bank bank;
    private Account account;
    private DashboardFrame dashboard;

    private JTextField receiverField;
    private JTextField amountField;

    public TransferFrame(Bank bank, Account account, DashboardFrame dashboard) {

        this.bank = bank;
        this.account = account;
        this.dashboard = dashboard;

        setTitle("Transfer Money");
        setSize(450,320);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(245,248,250));

        JLabel title = new JLabel("Transfer Money");
        title.setFont(new Font("Segoe UI", Font.BOLD,24));
        title.setForeground(new Color(0,102,204));
        title.setBounds(120,20,220,35);

        JLabel receiverLabel = new JLabel("Receiver ID");
        receiverLabel.setBounds(40,90,100,25);

        receiverField = new JTextField();
        receiverField.setBounds(150,90,220,30);

        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setBounds(40,145,100,25);

        amountField = new JTextField();
        amountField.setBounds(150,145,220,30);

        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(70,220,120,35);

        JButton backButton = new JButton("Back");
        backButton.setBounds(240,220,120,35);

        panel.add(title);
        panel.add(receiverLabel);
        panel.add(receiverField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(transferButton);
        panel.add(backButton);

        add(panel);

        transferButton.addActionListener(e -> transfer());

        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void transfer() {

        String receiver = receiverField.getText().trim();
        String amountText = amountField.getText().trim();

        if(receiver.isEmpty() || amountText.isEmpty()){

            JOptionPane.showMessageDialog(
                    this,
                    "Fill all fields.");

            return;
        }

        double amount;

        try{

            amount = Double.parseDouble(amountText);

        }catch(NumberFormatException ex){

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Amount.");

            return;
        }

        if(amount<=0){

            JOptionPane.showMessageDialog(
                    this,
                    "Amount must be greater than zero.");

            return;
        }

        if(bank.transfer(account, receiver, amount)){

            dashboard.refreshBalance();

            JOptionPane.showMessageDialog(
                    this,
                    "Transfer Successful!");

            dispose();

        }else{

            JOptionPane.showMessageDialog(
                    this,
                    "Transfer Failed!\nCheck Receiver ID or Balance.");

        }

    }

}