 package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import util.DBConnection;


public class TrackerDAO {

	private static final String INSERT_ORDER_SQL = "INSERT INTO orders" + "  (trackingNo, status,totalAmount,email,date) VALUES "
			+ " (?, ?, ?,?,?);";

	private static final String SELECT_ORDER_BY_ID = "select id,trackingNo,status,totalAmount,email,date,address from orders where id =?";
	private static final String SELECT_ALL_ORDERS = "select * from orders";
	private static final String DELETE_ORDER_SQL = "delete from orders where id = ?;";
	private static final String UPDATE_ORDER_SQL = "update orders set trackingNo = ?,status= ?,totalAmount = ?,email = ?,date = ?,address = ? where id = ?;";

	public TrackerDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		connection = DBConnection.getConnection();
		return connection;
	}

	public void insertOrder(Order order) throws SQLException {
		System.out.println(INSERT_ORDER_SQL);
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_SQL)) {
			preparedStatement.setString(1, order.getTrackingNo());
			preparedStatement.setString(2, order.getStatus());
			preparedStatement.setString(3, order.getTotalAmount());
			preparedStatement.setString(4, order.getEmail());
			preparedStatement.setString(5, order.getDate());
			preparedStatement.setString(6, order.getAddress());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Order selectOrder(int id) {
		Order order = null;
		
		try (Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				String trackingNo = rs.getString("trackingNo");
				String status = rs.getString("status");
				String totalAmount = rs.getString("totalAmount");
				String email = rs.getString("email");
				String date = rs.getString("date");
				String address = rs.getString("address");
				order = new Order(id, trackingNo, status,totalAmount,email,date,address);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return order;
	}

	public List<Order> selectAllOrders() {

		
		List<Order> order = new ArrayList<>();
		
		try (Connection connection = getConnection();

				
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);) {
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id = rs.getInt("id");
				String trackingNo = rs.getString("trackingNo");
				String status = rs.getString("status");
				String totalAmount = rs.getString("totalAmount");
				String email = rs.getString("email");
				String date = rs.getString("date");
				String address = rs.getString("address");
				order.add(new Order(id, trackingNo, status,totalAmount,email,date,address));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return order;
	}

	public boolean deleteOrder(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateOrder(Order order) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_SQL);) {
			statement.setString(1, order.getTrackingNo());
			statement.setString(2, order.getStatus());
			statement.setString(3, order.getTotalAmount());
			statement.setString(4, order.getEmail());
			statement.setString(5, order.getDate());
			statement.setString(6, order.getAddress());
			statement.setInt(7, order.getOrderId());

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
