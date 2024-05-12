package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReviewDAO;
import model.Review;


public class AddReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReviewDAO reviewDAO;

    public void init() {
        reviewDAO = new ReviewDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String desc = request.getParameter("des");


        Review newReview = new Review(name, desc);

        try {
        	reviewDAO.insertReview(newReview);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        response.sendRedirect("addreview.jsp");
    }
}
