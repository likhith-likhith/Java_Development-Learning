package com.likhith.atm.gui;

import com.likhith.atm.Account;
import com.likhith.atm.Bank;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField userField;
    private JPasswordField pinField;
    private JButton loginButton;

    private Bank bank;

    private int attempts = 3;

    public LoginFrame(Bank bank) {

        this.bank = bank;

        setTitle("LIKHITH BANK ATM");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(230, 240, 255));

        // ================= TITLE =================

        JLabel title = new JLabel("LIKHITH BANK ATM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(0, 70, 170));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(40, 80, 400, 35);

        // ================= WELCOME =================

        JLabel welcome = new JLabel("Welcome! Please login to continue");
        welcome.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setBounds(60, 120, 360, 25);

        // ================= USER ID =================

        JLabel userLabel = new JLabel("User ID");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        userLabel.setBounds(60, 170, 100, 25);

        userField = new PlaceholderTextField("Enter User ID");
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        userField.setBounds(60, 200, 360, 40);

        // ================= PIN =================

        JLabel pinLabel = new JLabel("PIN");
        pinLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        pinLabel.setBounds(60, 260, 100, 25);

        pinField = new PlaceholderPasswordField("Enter 4-digit PIN");
        pinField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        pinField.setBounds(60, 290, 360, 40);

        // ================= LOGIN BUTTON =================

        loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setBounds(150, 370, 200, 45);

        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        // ================= ADD COMPONENTS =================

        
        panel.add(title);
        panel.add(welcome);

        panel.add(userLabel);
        panel.add(userField);

        panel.add(pinLabel);
        panel.add(pinField);

        panel.add(loginButton);

        add(panel);

        getRootPane().setDefaultButton(loginButton);

        loginButton.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {

        String user = userField.getText().trim();
        String pin = new String(pinField.getPassword());

        if (user.equals("Enter User ID")
                || pin.equals("Enter 4-digit PIN")
                || user.isEmpty()
                || pin.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter User ID and PIN.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        Account account = bank.login(user, pin);

        if (account != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login Successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            new DashboardFrame(bank, account);

            dispose();

        } else {

            attempts--;

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid User ID or PIN.\nRemaining Attempts : " + attempts,
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);

            if (attempts == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Account Locked!\nApplication will now close.",
                        "Access Denied",
                        JOptionPane.ERROR_MESSAGE);

                System.exit(0);

            }

        }

    }

}