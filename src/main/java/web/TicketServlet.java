package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TicketDAO;
import model.Ticket;

@WebServlet("/addTicket")
public class TicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TicketDAO ticketDAO;

    public void init() {
        ticketDAO = new TicketDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String issue = request.getParameter("issue");

        Ticket newTicket = new Ticket(name, email, subject, issue);

        try {
            ticketDAO.insertTicket(newTicket);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        response.sendRedirect("addticket.jsp");
    }
}
