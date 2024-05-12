package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import util.DBConnection;

public class UserOrderDAO {
    private static final String SELECT_ORDERS_BY_EMAIL = "SELECT * FROM orders WHERE email = ?";
    

    public UserOrderDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        connection = DBConnection.getConnection();
        return connection;
    }

    public List<Order> selectOrdersByUserEmail(String email) {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_EMAIL)) {

            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String trackingNo = rs.getString("trackingNo");
                String status = rs.getString("status");
                String totalAmount = rs.getString("totalAmount");
                String date = rs.getString("date");
                String address = rs.getString("address");
                orders.add(new Order(id, trackingNo, status, totalAmount,date,address));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orders;
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
