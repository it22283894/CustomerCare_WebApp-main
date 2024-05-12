 package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ticket;
import util.DBConnection;


public class UserTicketDAO {

	private static final String INSERT_TICKET_SQL = "INSERT INTO ticket" + "  (name, email, subject, issue) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_TICKET_BY_ID = "select id,name,email,subject,issue from ticket where id =?";
	private static final String SELECT_ALL_TICKETS = "select * from ticket where email =?";
	private static final String DELETE_TICKET_SQL = "delete from ticket where id = ?;";
	private static final String UPDATE_TICKET_SQL = "update ticket set name = ?,email= ?, subject =?, issue =? where id = ?;";

	public UserTicketDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		connection = DBConnection.getConnection();
		return connection;
	}

	public void insertTicket(Ticket ticket) throws SQLException {
		System.out.println(INSERT_TICKET_SQL);
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET_SQL)) {
			preparedStatement.setString(1, ticket.getName());
			preparedStatement.setString(2, ticket.getEmail());
			preparedStatement.setString(3, ticket.getSubject());
			preparedStatement.setString(4, ticket.getIssue());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Ticket selectTicket(int id) {
		Ticket ticket = null;
		
		try (Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TICKET_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String subject = rs.getString("subject");
				String issue = rs.getString("issue");
				ticket = new Ticket(id, name, email, subject, issue);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return ticket;
	}

	public List<Ticket> selectAllUserTickets(String email) {

		
		List<Ticket> ticket = new ArrayList<>();
		
		try (Connection connection = getConnection();

			
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TICKETS);) {
			System.out.println(preparedStatement);
			
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String useremail = rs.getString("email");
				String subject = rs.getString("subject");
				String issue = rs.getString("issue");
				ticket.add(new Ticket(id, name,useremail, subject, issue));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return ticket;
	}

	public boolean deleteTicket(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_TICKET_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateTicket(Ticket ticket) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_TICKET_SQL);) {
			statement.setString(1, ticket.getName());
			statement.setString(2, ticket.getEmail());
			statement.setString(3, ticket.getSubject());
			statement.setString(4, ticket.getIssue());
			statement.setInt(5, ticket.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
