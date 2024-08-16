package org.example.dao;

import org.example.DatabaseConnection;
import org.example.models.Ticket;
import org.example.models.TicketType;
import org.example.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTicketDAO {

    public void saveUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, creation_date) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, user.getName());
            pstmt.setTimestamp(2, user.getCreationDate());
            pstmt.executeUpdate();
        }
    }

    public void saveTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO tickets (user_id, ticket_type, creation_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, ticket.getUserId());
            pstmt.setObject(2, ticket.getTicketType().name(), java.sql.Types.OTHER);
            pstmt.setTimestamp(3, ticket.getCreationDate());
            pstmt.executeUpdate();
        }
    }

    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setCreationDate(rs.getTimestamp("creation_date"));
                return user;
            }
        }
        return null;
    }

    public Ticket getTicketById(int id) throws SQLException {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setUserId(rs.getInt("user_id"));
                ticket.setTicketType(TicketType.valueOf(rs.getString("ticket_type")));
                ticket.setCreationDate(rs.getTimestamp("creation_date"));
                return ticket;
            }
        }
        return null;
    }

    public List<Ticket> getTicketsByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM tickets WHERE user_id = ?";
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setUserId(rs.getInt("user_id"));
                ticket.setTicketType(TicketType.valueOf(rs.getString("ticket_type")));
                ticket.setCreationDate(rs.getTimestamp("creation_date"));
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    public void updateTicketType(int ticketId, TicketType ticketType) throws SQLException {
        String sql = "UPDATE tickets SET ticket_type = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, ticketType.name(), java.sql.Types.OTHER);
            pstmt.setInt(2, ticketId);
            pstmt.executeUpdate();
        }
    }

    public void deleteUserById(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
