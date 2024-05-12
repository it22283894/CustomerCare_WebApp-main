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
import javax.servlet.http.HttpSession;

import dao.UserOrderDAO;


public class UserOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserOrderDAO orderDAO;
		
	
	public void init() {
		orderDAO = new UserOrderDAO();
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
			case "/userorderlist":
				listUserOrder(request, response);
				break;
			default:
				//listOrder(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}


    private void listUserOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("email");

        if (userEmail != null && !userEmail.isEmpty()) {
            List<Order> listUserOrder = orderDAO.selectOrdersByUserEmail(userEmail);
            request.setAttribute("listUserOrder", listUserOrder);
            RequestDispatcher dispatcher = request.getRequestDispatcher("userorder.jsp");
            dispatcher.forward(request, response);
        } else {
            /////
        }
    }





}
