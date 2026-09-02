package com.likhith.atm.gui;

import com.likhith.atm.Account;
import com.likhith.atm.Bank;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardFrame extends JFrame {

    private Bank bank;
    private Account account;

    private JLabel balanceLabel;
    private JLabel dateTimeLabel;

    public DashboardFrame(Bank bank, Account account) {

        this.bank = bank;
        this.account = account;

        setTitle("LIKHITH BANK ATM");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 240, 255));

        dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateTimeLabel.setBounds(370, 10, 250, 40);

        // ================= TITLE =================

        JLabel title = new JLabel("LIKHITH BANK ATM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(0, 51, 153));
        title.setBounds(140, 20, 400, 40);

        // ================= WELCOME =================

        JLabel welcome = new JLabel("Welcome, " + account.getUserId());
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 18));
        welcome.setBounds(210, 75, 250, 25);

        JLabel accountLabel = new JLabel("Account : " + account.getAccountNumber());
        accountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        accountLabel.setBounds(210, 105, 250, 25);

        // ================= BALANCE =================

        JLabel balanceTitle = new JLabel("Current Balance");
        balanceTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        balanceTitle.setBounds(150, 145, 350, 25);
        balanceTitle.setHorizontalAlignment(SwingConstants.CENTER);

        balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 28));
        balanceLabel.setForeground(new Color(0, 140, 0));
        balanceLabel.setBounds(150, 175, 350, 35);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);

        refreshBalance();

        // ================= BUTTONS =================

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(60, 250, 220, 45);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(340, 250, 220, 45);

        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(60, 320, 220, 45);

        JButton historyButton = new JButton("History");
        historyButton.setBounds(340, 320, 220, 45);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(60, 390, 220, 45);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(340, 390, 220, 45);

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 15);

        depositButton.setFont(buttonFont);
        withdrawButton.setFont(buttonFont);
        transferButton.setFont(buttonFont);
        historyButton.setFont(buttonFont);
        logoutButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        // ================= ADD COMPONENTS =================

        panel.add(dateTimeLabel);
        panel.add(title);
        panel.add(welcome);
        panel.add(accountLabel);
        panel.add(balanceTitle);
        panel.add(balanceLabel);

        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(transferButton);
        panel.add(historyButton);
        panel.add(logoutButton);
        panel.add(exitButton);

        add(panel);

        // ================= ACTIONS =================

        depositButton.addActionListener(e ->
                new DepositFrame(bank, account, this));

        withdrawButton.addActionListener(e ->
                new WithdrawFrame(bank, account, this));

        transferButton.addActionListener(e ->
                new TransferFrame(bank, account, this));

        historyButton.addActionListener(e ->
                new HistoryFrame(bank));

        logoutButton.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                new LoginFrame(bank);
                dispose();
            }

        });

        exitButton.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        });

        setVisible(true);
        startClock();
    }

    public void refreshBalance() {

        balanceLabel.setText("\u20B9 " + String.format("%,.2f", account.getBalance()));

    }
    private void startClock(){
        Timer timer = new Timer(1000, e -> {
                SimpleDateFormat sdf =new SimpleDateFormat("dd-MMM-yyyy\nhh:mm:ss a");
                String date =new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
                String time =new SimpleDateFormat("hh:mm:ss a").format(new Date());
                dateTimeLabel.setText("<html>" + date + "<br>" + time + "</html>");
        });
        timer.start();
}}
