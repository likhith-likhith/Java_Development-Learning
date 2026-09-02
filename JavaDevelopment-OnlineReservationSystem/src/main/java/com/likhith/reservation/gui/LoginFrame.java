package com.likhith.reservation.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import com.likhith.reservation.dao.LoginDAO;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle(" LIKHITH Online Reservation System");
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 248, 250));

        JLabel heading = new JLabel("ONLINE RESERVATION SYSTEM");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 18));
        heading.setBounds(45, 20, 330, 30);
        heading.setHorizontalAlignment(JLabel.CENTER);

        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setBounds(50, 80, 100, 25);

        usernameField = new PlaceholderTextField("Enter Username");
        usernameField.setBounds(150, 80, 180, 30);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passLabel.setBounds(50, 130, 100, 25);

        passwordField = new PlaceholderPasswordField("Enter Password");
        passwordField.setBounds(150, 130, 180, 30);
        ((javax.swing.text.AbstractDocument) passwordField.getDocument())
                .setDocumentFilter(new javax.swing.text.DocumentFilter() {

                    @Override
                    public void insertString(FilterBypass fb,
                            int offset,
                            String string,
                            javax.swing.text.AttributeSet attr)
                            throws javax.swing.text.BadLocationException {

                        if (fb.getDocument().getLength() + string.length() <= 8) {
                            super.insertString(fb, offset, string, attr);
                        }
                    }

                    @Override
                    public void replace(FilterBypass fb,
                            int offset,
                            int length,
                            String text,
                            javax.swing.text.AttributeSet attrs)
                            throws javax.swing.text.BadLocationException {

                        if (fb.getDocument().getLength() - length + text.length() <= 8) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }
                });

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBounds(150, 190, 180, 35);

        panel.add(heading);
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            LoginFrame.this,
                            "Please enter Username and Password!",
                            "Input Error",
                            JOptionPane.WARNING_MESSAGE);

                    return;
                }

                LoginDAO loginDAO = new LoginDAO();

                if (loginDAO.validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(
                            LoginFrame.this,
                            "Login Successful!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Open Dashboard
                    new DashboardFrame();

                    // Close Login Window
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                            LoginFrame.this,
                            "Access Denied!\nInvalid Username or Password.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);

                    passwordField.setText("");
                }
            }
        });

        setVisible(true);
    }
}