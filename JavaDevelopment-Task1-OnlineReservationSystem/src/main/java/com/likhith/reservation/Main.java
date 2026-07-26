package com.likhith.reservation;

import javax.swing.SwingUtilities;

import com.likhith.reservation.database.DatabaseInitializer;
import com.likhith.reservation.gui.LoginFrame;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

        SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });

    }
}