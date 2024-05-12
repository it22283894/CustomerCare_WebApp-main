 package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;
import util.DBConnection;


public class ManageReviewDAO {

	private static final String INSERT_REVIEW_SQL = "INSERT INTO review" + "  (name, des) VALUES "
			+ " (?, ?);";

	private static final String SELECT_REVIEW_BY_ID = "select id,name,des from review where id =?";
	private static final String SELECT_ALL_REVIEWS = "select * from review";
	private static final String DELETE_REVIEW_SQL = "delete from review where id = ?;";
	private static final String UPDATE_REVIEW_SQL = "update review set name = ?,des= ? where id = ?;";

	public ManageReviewDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		connection = DBConnection.getConnection();
		return connection;
	}

	public void insertReview(Review review) throws SQLException {
		System.out.println(INSERT_REVIEW_SQL);
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REVIEW_SQL)) {
			preparedStatement.setString(1, review.getName());
			preparedStatement.setString(2, review.getDesc());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Review selectReview(int id) {
		Review review = null;
		
		try (Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVIEW_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				String name = rs.getString("name");
				String desc = rs.getString("des");
				review = new Review(id, name, desc);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return review;
	}

	public List<Review> selectAllReviews() {

		
		List<Review> review = new ArrayList<>();
		
		try (Connection connection = getConnection();

				
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REVIEWS);) {
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String desc = rs.getString("des");
				review.add(new Review(id, name, desc));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return review;
	}

	public boolean deleteReview(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_REVIEW_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateReview(Review review) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_REVIEW_SQL);) {
			statement.setString(1, review.getName());
			statement.setString(2, review.getDesc());
			statement.setInt(3, review.getId());

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
