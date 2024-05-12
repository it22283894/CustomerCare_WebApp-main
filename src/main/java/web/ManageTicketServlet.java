package web;


import model.Ticket;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageTicketDAO;


public class ManageTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManageTicketDAO ticketDAO;
	
	public void init() {
		ticketDAO = new ManageTicketDAO();
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
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertTicket(request, response);
				break;
			case "/delete":
				deleteTicket(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateTicket(request, response);
				break;
			default:
				listTicket(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listTicket(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Ticket> listTicket = ticketDAO.selectAllTickets();
		request.setAttribute("listTicket", listTicket);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ticket-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("ticket-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Ticket existingTicket = ticketDAO.selectTicket(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ticket-form.jsp");
		request.setAttribute("ticket", existingTicket);
		dispatcher.forward(request, response);

	}

	private void insertTicket(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String issue = request.getParameter("issue");
		Ticket newTicket = new Ticket(name, email, subject, issue);
		ticketDAO.insertTicket(newTicket);
		response.sendRedirect("list");
	}

	private void updateTicket(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String issue = request.getParameter("issue");

		Ticket tck = new Ticket(id, name, email, subject,issue);
		ticketDAO.updateTicket(tck);
		response.sendRedirect("list");
	}

	private void deleteTicket(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ticketDAO.deleteTicket(id);
		response.sendRedirect("list");

	}

}
