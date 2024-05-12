package web;


import model.Review;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageReviewDAO;


public class ManageReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManageReviewDAO reviewDAO;
	
	public void init() {
		reviewDAO = new ManageReviewDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/newrv":
				showrvNewForm(request, response);
				break;
			case "/insertrv":
				insertReview(request, response);
				break;
			case "/deleterv":
				deleteReview(request, response);
				break;
			case "/editrv":
				showrvEditForm(request, response);
				break;
			case "/updaterv":
				updateReview(request, response);
				break;
			default:
				listReview(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listReview(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Review> listReview = reviewDAO.selectAllReviews();
		request.setAttribute("listReview", listReview);
		RequestDispatcher dispatcher = request.getRequestDispatcher("review-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showrvNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("review-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showrvEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Review existingReview = reviewDAO.selectReview(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("review-form.jsp");
		request.setAttribute("review", existingReview);
		dispatcher.forward(request, response);

	}

	private void insertReview(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String desc = request.getParameter("des");
		
		Review newReview = new Review(name, desc);
		reviewDAO.insertReview(newReview);
		response.sendRedirect("rvlist");
	}

	private void updateReview(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String desc = request.getParameter("des");
		Review rv = new Review(id, name, desc);
		reviewDAO.updateReview(rv);
		response.sendRedirect("rvlist");
	}

	private void deleteReview(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		reviewDAO.deleteReview(id);
		response.sendRedirect("rvlist");

	}

}
