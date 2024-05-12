 package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Faq;
import util.DBConnection;


public class ManageFaqDAO {

	private static final String INSERT_FAQ_SQL = "INSERT INTO faq" + "  (category, problem, solution,author) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_FAQ_BY_ID = "select id,category,problem,solution,author from faq where id =?";
	private static final String SELECT_ALL_FAQS = "select * from faq";
	private static final String DELETE_FAQ_SQL = "delete from faq where id = ?;";
	private static final String UPDATE_FAQ_SQL = "update faq set category = ?,problem= ?, solution =?, author =? where id = ?;";

	public ManageFaqDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		connection = DBConnection.getConnection();
		return connection;
	}

	public void insertFaq(Faq faq) throws SQLException {
		System.out.println(INSERT_FAQ_SQL);
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FAQ_SQL)) {
			preparedStatement.setString(1, faq.getCategory());
			preparedStatement.setString(2, faq.getProblem());
			preparedStatement.setString(3, faq.getSolution());
			preparedStatement.setString(4, faq.getAuthor());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Faq selectFaq(int id) {
		Faq faq = null;
		
		try (Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FAQ_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
	
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				String category = rs.getString("category");
				String problem = rs.getString("problem");
				String solution = rs.getString("solution");
				String author = rs.getString("author");
				faq = new Faq(id, category, problem, solution, author);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return faq;
	}

	public List<Faq> selectAllFaqs() {

		
		List<Faq> faq = new ArrayList<>();
		
		try (Connection connection = getConnection();

				
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FAQS);) {
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String problem = rs.getString("problem");
				String solution = rs.getString("solution");
				String author = rs.getString("author");
				faq.add(new Faq(id, category, problem, solution, author));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return faq;
	}

	public boolean deleteFaq(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_FAQ_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateFaq(Faq faq) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_FAQ_SQL);) {
			statement.setString(1, faq.getCategory());
			statement.setString(2, faq.getProblem());
			statement.setString(3, faq.getSolution());
			statement.setString(4, faq.getAuthor());
			statement.setInt(5, faq.getId());

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
