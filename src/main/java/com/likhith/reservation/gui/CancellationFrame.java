package com.likhith.reservation.gui;

import com.likhith.reservation.dao.ReservationDAO;
import com.likhith.reservation.model.Reservation;

import javax.swing.*;
import java.awt.*;

public class CancellationFrame extends JFrame {

    private JTextField pnrField;

    private JLabel passengerLabel;
    private JLabel trainLabel;
    private JLabel classLabel;
    private JLabel dateLabel;
    private JLabel sourceLabel;
    private JLabel destinationLabel;

    private JButton fetchButton;
    private JButton cancelButton;
    private JButton backButton;

    private Reservation currentReservation;

    public CancellationFrame() {

        setTitle("Cancel Reservation");
        setSize(650,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245,248,250));

        JLabel title=new JLabel("CANCEL RESERVATION");
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setForeground(new Color(0,102,204));
        title.setBounds(170,20,350,35);

        JLabel pnr=new JLabel("PNR Number");
        pnr.setBounds(70,80,120,25);

        pnrField=new JTextField();
        pnrField.setBounds(190,80,220,30);

        fetchButton=new JButton("Fetch Booking");
        fetchButton.setBounds(430,80,140,30);

        JLabel details=new JLabel("Booking Details");
        details.setFont(new Font("Segoe UI",Font.BOLD,18));
        details.setBounds(70,140,200,30);

        passengerLabel=new JLabel("Passenger : ");
        passengerLabel.setBounds(70,190,500,25);

        trainLabel=new JLabel("Train : ");
        trainLabel.setBounds(70,225,500,25);

        classLabel=new JLabel("Class : ");
        classLabel.setBounds(70,260,500,25);

        dateLabel=new JLabel("Journey Date : ");
        dateLabel.setBounds(70,295,500,25);

        sourceLabel=new JLabel("Source : ");
        sourceLabel.setBounds(70,330,500,25);

        destinationLabel=new JLabel("Destination : ");
        destinationLabel.setBounds(70,365,500,25);

        cancelButton=new JButton("Cancel Reservation");
        cancelButton.setBounds(130,440,170,40);

        backButton=new JButton("Back");
        backButton.setBounds(340,440,120,40);

        panel.add(title);
        panel.add(pnr);
        panel.add(pnrField);
        panel.add(fetchButton);
        panel.add(details);
        panel.add(passengerLabel);
        panel.add(trainLabel);
        panel.add(classLabel);
        panel.add(dateLabel);
        panel.add(sourceLabel);
        panel.add(destinationLabel);
        panel.add(cancelButton);
        panel.add(backButton);

        add(panel);

        fetchButton.addActionListener(e->{

            String pnrNo=pnrField.getText().trim();

            if(pnrNo.isEmpty()){

                JOptionPane.showMessageDialog(
                        this,
                        "Enter PNR Number");

                return;
            }

            ReservationDAO dao=new ReservationDAO();

            currentReservation=dao.getReservationByPNR(pnrNo);

            if(currentReservation==null){

                JOptionPane.showMessageDialog(
                        this,
                        "Reservation Not Found!");

                clearLabels();

                return;
            }

            passengerLabel.setText(
                    "Passenger : "+currentReservation.getPassengerName());

            trainLabel.setText(
                    "Train : "+currentReservation.getTrainName());

            classLabel.setText(
                    "Class : "+currentReservation.getClassType());

            dateLabel.setText(
                    "Journey Date : "+currentReservation.getJourneyDate());

            sourceLabel.setText(
                    "Source : "+currentReservation.getSourceStation());

            destinationLabel.setText(
                    "Destination : "+currentReservation.getDestinationStation());

        });
        cancelButton.addActionListener(e -> {

            if (currentReservation == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fetch a reservation first!");

                return;
            }

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to cancel this reservation?",
                    "Confirm Cancellation",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {

                ReservationDAO dao = new ReservationDAO();

                if (dao.cancelReservation(currentReservation.getPnr())) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Reservation Cancelled Successfully!");

                    pnrField.setText("");
                    currentReservation = null;
                    clearLabels();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Cancellation Failed!");

                }
            }
        });

        backButton.addActionListener(e -> {
            dispose();
        });

        setVisible(true);
    }

    private void clearLabels() {

        passengerLabel.setText("Passenger : ");
        trainLabel.setText("Train : ");
        classLabel.setText("Class : ");
        dateLabel.setText("Journey Date : ");
        sourceLabel.setText("Source : ");
        destinationLabel.setText("Destination : ");

    }
}