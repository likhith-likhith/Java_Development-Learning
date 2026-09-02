package com.likhith.reservation.dao;

import com.likhith.reservation.database.DBConnection;
import com.likhith.reservation.model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationDAO {

    public boolean bookTicket(Reservation reservation) {

        String sql = """
                INSERT INTO reservations
                (pnr, passenger_name, train_number, train_name,
                 class_type, journey_date, source_station, destination_station)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, reservation.getPnr());
            ps.setString(2, reservation.getPassengerName());
            ps.setInt(3, reservation.getTrainNumber());
            ps.setString(4, reservation.getTrainName());
            ps.setString(5, reservation.getClassType());
            ps.setString(6, reservation.getJourneyDate());
            ps.setString(7, reservation.getSourceStation());
            ps.setString(8, reservation.getDestinationStation());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Reservation getReservationByPNR(String pnr) {
        String sql = "SELECT * FROM reservations WHERE pnr = ?";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pnr);
            var rs = ps.executeQuery();
            if (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setPnr(rs.getString("pnr"));
                reservation.setPassengerName(rs.getString("passenger_name"));
                reservation.setTrainNumber(rs.getInt("train_number"));
                reservation.setTrainName(rs.getString("train_name"));
                reservation.setClassType(rs.getString("class_type"));
                reservation.setJourneyDate(rs.getString("journey_date"));
                reservation.setSourceStation(rs.getString("source_station"));
                reservation.setDestinationStation(rs.getString("destination_station"));
                return reservation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean cancelReservation(String pnr) {
        String sql = "DELETE FROM reservations WHERE pnr = ?";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pnr);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}