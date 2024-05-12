package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Question;
import util.DBConnection;

@WebServlet("/askquestion")
public class AskQuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
       
        String question = request.getParameter("question");

        
        
        Question q = new Question(question);

     
        Connection connection = DBConnection.getConnection();
        try {
            String insertQuery = "INSERT INTO question  VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        
            preparedStatement.setString(1, q.getQuestion());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection);
        }

       
        response.sendRedirect("landing.jsp");
    }
}
