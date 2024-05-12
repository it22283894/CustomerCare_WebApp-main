package web;


import model.Order;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.OrderDAO;


public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO;
	
	public void init() {
		orderDAO = new OrderDAO();
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
			case "/neworder":
				showrvNewOrderForm(request, response);
				break;
			case "/insertorder":
				insertOrder(request, response);
				break;
			case "/deleteorder":
				deleteOrder(request, response);
				break;
			case "/editorder":
				showrvEditOrderForm(request, response);
				break;
			case "/updateorder":
				updateOrder(request, response);
				break;
			default:
				listOrder(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Order> listOrder = orderDAO.selectAllOrders();
		request.setAttribute("listOrder", listOrder);
		RequestDispatcher dispatcher = request.getRequestDispatcher("order-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showrvNewOrderForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("order-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showrvEditOrderForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Order existingOrder = orderDAO.selectOrder(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("order-form.jsp");
		request.setAttribute("order", existingOrder);
		dispatcher.forward(request, response);

	}

	private void insertOrder(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String trackingNo = request.getParameter("trackingNo");
		String status = request.getParameter("status");
		String totalAmount = request.getParameter("totalAmount");
		String email = request.getParameter("email");
		String date = request.getParameter("date");
		String address = request.getParameter("address");
		
		Order newOrder = new Order(trackingNo, status,totalAmount,email,date,address);
		orderDAO.insertOrder(newOrder);
		response.sendRedirect("orderlist");
	}

	private void updateOrder(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		String trackingNo = request.getParameter("trackingNo");
		String status = request.getParameter("status");
		String totalAmount = request.getParameter("totalAmount");
		String email = request.getParameter("email");
		String date = request.getParameter("date");
		String address = request.getParameter("address");
		Order order = new Order(orderId, trackingNo, status,totalAmount,email,date,address);
		orderDAO.updateOrder(order);
		response.sendRedirect("orderlist");
	}

	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		orderDAO.deleteOrder(id);
		response.sendRedirect("orderlist");

	}

}
