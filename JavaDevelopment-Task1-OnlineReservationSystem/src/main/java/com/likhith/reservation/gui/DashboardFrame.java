package com.likhith.reservation.gui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private JButton reservationButton;
    private JButton cancelButton;
    private JButton logoutButton;

    public DashboardFrame() {

        setTitle("Online Reservation System - Dashboard");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 245, 250));

        JLabel title = new JLabel("ONLINE RESERVATION SYSTEM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(0, 102, 204));
        title.setBounds(170, 30, 500, 40);

        JLabel welcome = new JLabel("Welcome, Admin");
        welcome.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        welcome.setBounds(315, 80, 200, 30);

        JLabel date = new JLabel("Date : " + java.time.LocalDate.now());
        date.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        date.setBounds(315, 110, 250, 25);

        reservationButton = new RoundedButton("New Reservation");
        reservationButton.setBounds(270, 170, 250, 50);

        cancelButton = new RoundedButton("Cancel Reservation");
        cancelButton.setBounds(270, 250, 250, 50);

        logoutButton = new RoundedButton("Logout");
        logoutButton.setBounds(270, 330, 250, 50);

        panel.add(title);
        panel.add(welcome);
        panel.add(date);
        panel.add(reservationButton);
        panel.add(cancelButton);
        panel.add(logoutButton);

        add(panel);

        reservationButton.addActionListener(e -> {
            new ReservationFrame();
        });

        cancelButton.addActionListener(e -> {
            new CancellationFrame();
        });

        logoutButton.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to Logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {

                new LoginFrame();

                dispose();
            }

        });

        setVisible(true);
    }
}
