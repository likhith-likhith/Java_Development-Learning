package com.likhith.reservation.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() {

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create users table
            String usersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    username TEXT PRIMARY KEY,
                    password TEXT NOT NULL
                );
                """;

            stmt.execute(usersTable);

            // Create reservations table
            String reservationTable = """
                CREATE TABLE IF NOT EXISTS reservations (
                    pnr TEXT PRIMARY KEY,
                    passenger_name TEXT NOT NULL,
                    train_number INTEGER NOT NULL,
                    train_name TEXT NOT NULL,
                    class_type TEXT NOT NULL,
                    journey_date TEXT NOT NULL,
                    source_station TEXT NOT NULL,
                    destination_station TEXT NOT NULL
                );
                """;

            stmt.execute(reservationTable);

            // Insert default admin user
            String insertAdmin = """
                INSERT OR IGNORE INTO users(username, password)
                VALUES(?, ?);
                """;

            PreparedStatement ps = conn.prepareStatement(insertAdmin);
            ps.setString(1, "admin");
            ps.setString(2, "admin123");
            ps.executeUpdate();

            System.out.println("Database initialized successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}