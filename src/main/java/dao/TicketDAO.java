package dao;


import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Ticket;


public class TicketDAO{

   private static final String INSERT_TICKET_SQL = "INSERT INTO ticket" +
            "  (name, email, subject, issue) VALUES (?, ?, ?, ?);";

   public void insertTicket(Ticket ticket) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET_SQL)) {
            preparedStatement.setString(3, ticket.getName());
            preparedStatement.setString(2, ticket.getEmail());
            preparedStatement.setString(1, ticket.getSubject());
            preparedStatement.setString(4, ticket.getIssue());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
