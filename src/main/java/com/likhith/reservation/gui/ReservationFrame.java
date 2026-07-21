package com.likhith.reservation.gui;

import javax.swing.*;
import java.awt.*;
import com.likhith.reservation.dao.ReservationDAO;
import com.likhith.reservation.model.Reservation;
import com.likhith.reservation.util.PNRGenerator;
import com.toedter.calendar.JDateChooser;

public class ReservationFrame extends JFrame {

    private JTextField passengerField;
    private JComboBox<String> trainNumberField;
    private final String[] trainNumbers = {
        "12049",
        "12627",
        "12628",
        "12701",
        "12702",
        "12951",
        "12615",
        "12839"
    };
    private JTextField trainNameField;
    private JComboBox<String> classCombo;
    private JDateChooser journeyDateChooser;
    private JTextField sourceField;
    private JTextField destinationField;

    private JButton bookButton;
    private JButton backButton;

    public ReservationFrame() {
        setTitle("Train Reservation");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 248, 250));

        JLabel title = new JLabel("TRAIN RESERVATION");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBounds(180, 20, 300, 35);

        JLabel l1 = new JLabel("Passenger Name");
        l1.setBounds(60, 80, 150, 25);

        passengerField = new JTextField();
        passengerField.setBounds(230, 80, 300, 30);

        JLabel l2 = new JLabel("Train Number");
        l2.setBounds(60, 125, 150, 25);

        trainNumberField = new JComboBox<>(trainNumbers);
        trainNumberField.setEditable(true);
        trainNumberField.setBounds(230, 125, 300, 30);

        JTextField editor = (JTextField) trainNumberField.getEditor().getEditorComponent();

        editor.setText("");
        editor.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                SwingUtilities.invokeLater(() -> editor.selectAll());
            }
        });

        editor.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String text = editor.getText();

                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

                boolean found = false;

                for (String train : trainNumbers) {
                    if (train.startsWith(text)) {
                        model.addElement(train);
                        found = true;
                    }
                }

                if (!found) {
                    model.addElement("TRAIN NOT FOUND");
                }

                trainNumberField.setModel(model);
                trainNumberField.getEditor().setItem(text);
                trainNumberField.showPopup();

                fillTrainName(text);
            }
        });

        trainNumberField.addActionListener(e -> {
            Object selected = trainNumberField.getSelectedItem();

            if (selected != null) {
                fillTrainName(selected.toString());
            }
        });

        JLabel l3 = new JLabel("Train Name");
        l3.setBounds(60, 170, 150, 25);

        trainNameField = new JTextField();
        trainNameField.setBounds(230, 170, 300, 30);
        trainNameField.setEditable(false);

        JLabel l4 = new JLabel("Class Type");
        l4.setBounds(60, 215, 150, 25);

        classCombo = new JComboBox<>(new String[] {
            "Sleeper",
            "3A",
            "2A",
            "1A"
        });

        classCombo.setBounds(230, 215, 300, 30);

        JLabel l5 = new JLabel("Journey Date");
        l5.setBounds(60, 260, 150, 25);

        journeyDateChooser = new JDateChooser();
        journeyDateChooser.setBounds(230, 260, 300, 30);
        journeyDateChooser.setDateFormatString("yyyy-MM-dd");
        journeyDateChooser.setDate(new java.util.Date());

        JLabel l6 = new JLabel("Source Station");
        l6.setBounds(60, 305, 150, 25);

        sourceField = new JTextField();
        sourceField.setBounds(230, 305, 300, 30);

        JLabel l7 = new JLabel("Destination");
        l7.setBounds(60, 350, 150, 25);

        destinationField = new JTextField();
        destinationField.setBounds(230, 350, 300, 30);

        bookButton = new JButton("Book Ticket");
        bookButton.setBounds(180, 410, 140, 35);

        backButton = new JButton("Back");
        backButton.setBounds(350, 410, 140, 35);

        panel.add(title);
        panel.add(l1);
        panel.add(passengerField);
        panel.add(l2);
        panel.add(trainNumberField);
        panel.add(l3);
        panel.add(trainNameField);
        panel.add(l4);
        panel.add(classCombo);
        panel.add(l5);
        panel.add(journeyDateChooser);
        panel.add(l6);
        panel.add(sourceField);
        panel.add(l7);
        panel.add(destinationField);
        panel.add(bookButton);
        panel.add(backButton);

        add(panel);
        bookButton.addActionListener(e -> {
            if (!validateFields()) {
                return;
            }
            Reservation reservation = new Reservation();
            reservation.setPnr(PNRGenerator.generatePNR());
            reservation.setPassengerName(passengerField.getText().trim());
            reservation.setTrainNumber(Integer.parseInt(trainNumberField.getSelectedItem().toString()));
            reservation.setTrainName(trainNameField.getText().trim());
            reservation.setClassType(classCombo.getSelectedItem().toString());
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            reservation.setJourneyDate(sdf.format(journeyDateChooser.getDate()));
            reservation.setSourceStation(sourceField.getText().trim());
            reservation.setDestinationStation(destinationField.getText().trim());
            ReservationDAO dao = new ReservationDAO();
            if (dao.bookTicket(reservation)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Ticket Booked Successfully!\n\n"
                        + "PNR : " + reservation.getPnr()
                        + "\nPassenger : " + reservation.getPassengerName()
                        + "\nTrain : " + reservation.getTrainName(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                passengerField.setText("");
                trainNumberField.setSelectedItem("");
                trainNameField.setText("");
                sourceField.setText("");
                journeyDateChooser.setDate(new java.util.Date());
                destinationField.setText("");
                classCombo.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Booking Failed!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        backButton.addActionListener(e -> {
            new DashboardFrame();
            dispose();
        });
        setVisible(true);
    }

    private void fillTrainName(String trainNo) {
        switch (trainNo) {
            case "12627":
                trainNameField.setText("Karnataka Express");
                break;
            case "12628":
                trainNameField.setText("Karnataka Express Return");
                break;
            case "12701":
                trainNameField.setText("Hussain Sagar Express");
                break;
            case "12702":
                trainNameField.setText("Golconda Express");
                break;
            case "12049":
                trainNameField.setText("Gatimaan Express");
                break;
            case "12951":
                trainNameField.setText("Mumbai Rajdhani");
                break;
            case "12615":
                trainNameField.setText("Grand Trunk Express");
                break;
            case "12839":
                trainNameField.setText("Howrah Mail");
                break;
            default:
                trainNameField.setText("TRAIN NOT FOUND");
        }
    }

    private boolean validateFields() {
        if (passengerField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Passenger Name");
            return false;
        }
        if (trainNumberField.getSelectedItem() == null
                || trainNumberField.getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Train Number");
            return false;
        }
        if (trainNameField.getText().trim().isEmpty() || trainNameField.getText().equals("Unknown Train")) {
            JOptionPane.showMessageDialog(this, "Enter Valid Train Number");
            return false;
        }
        if (journeyDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Select Journey Date");
            return false;
        }
        if (sourceField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Source Station");
            return false;
        }
        if (destinationField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Destination Station");
            return false;
        }
        try {
            Integer.parseInt(trainNumberField.getSelectedItem().toString().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Train Number must be numeric.");
            return false;
        }
        if (sourceField.getText().trim().equalsIgnoreCase(destinationField.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Source and Destination cannot be the same.");
            return false;
        }
        return true;
    }
}
