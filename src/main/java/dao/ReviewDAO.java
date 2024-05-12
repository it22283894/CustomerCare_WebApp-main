package dao;


import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Review;



public class ReviewDAO{

   private static final String INSERT_REVIEW_SQL = "INSERT INTO review" +
            "  (name, des) VALUES (?, ?);";

   public void insertReview(Review review) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REVIEW_SQL)) {
            preparedStatement.setString(1, review.getName());
            preparedStatement.setString(2, review.getDesc());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

