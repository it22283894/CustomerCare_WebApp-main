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
import javax.servlet.http.HttpSession;

import dao.UserTicketDAO;


public class UserTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserTicketDAO ticketDAO;
	
	public void init() {
		ticketDAO = new UserTicketDAO();
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
			case "/newuserticket":
				showNewForm(request, response);
				break;
			case "/insertuserticket":
				insertTicket(request, response);
				break;
			case "/deleteuserticket":
				deleteTicket(request, response);
				break;
			case "/edituserticket":
				showEditForm(request, response);
				break;
			case "/updateuserticket":
				updateTicket(request, response);
				break;
			case "/userticketlist":
				listTicket(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listTicket(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("email");

        if (userEmail != null && !userEmail.isEmpty()) {
            List<Ticket> listUserTicket = ticketDAO.selectAllUserTickets(userEmail);
            request.setAttribute("listUserTicket", listUserTicket);
            RequestDispatcher dispatcher = request.getRequestDispatcher("userticket.jsp");
            dispatcher.forward(request, response);
        } else {
            /////
        }
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("userticket-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Ticket existingTicket = ticketDAO.selectTicket(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("userticket-form.jsp");
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
		response.sendRedirect("userticketlist");
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
		response.sendRedirect("userticketlist");
	}

	private void deleteTicket(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ticketDAO.deleteTicket(id);
		response.sendRedirect("userticketlist");

	}

}
