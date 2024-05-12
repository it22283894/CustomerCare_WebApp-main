package web;


import model.Faq;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageFaqDAO;



public class ManageFaqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManageFaqDAO faqDAO;
	
	public void init() {
		faqDAO = new ManageFaqDAO();
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
			case "/newfaq":
				showfaqNewForm(request, response);
				break;
			case "/insertfaq":
				insertFaq(request, response);
				break;
			case "/deletefaq":
				deleteFaq(request, response);
				break;
			case "/editfaq":
				showfaqEditForm(request, response);
				break;
			case "/updatefaq":
				updateFaq(request, response);
				break;
			case "/homefaqlist":
				listFaqHome(request, response);
				break;
			case "/landingfaqlist":
				listFaqLanding(request, response);
				break;
			default:
				listFaq(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listFaq(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Faq> listFaq = faqDAO.selectAllFaqs();
		request.setAttribute("listFaq", listFaq);
		RequestDispatcher dispatcher = request.getRequestDispatcher("faq-list.jsp");
		dispatcher.forward(request, response);	
	}
	
	private void listFaqHome(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Faq> listFaq = faqDAO.selectAllFaqs();
		request.setAttribute("listFaq", listFaq);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listFaqLanding(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Faq> listFaq = faqDAO.selectAllFaqs();
		request.setAttribute("listFaq", listFaq);
		RequestDispatcher dispatcher = request.getRequestDispatcher("landing.jsp");
		dispatcher.forward(request, response);
	}

	private void showfaqNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("faq-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showfaqEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Faq existingFaq = faqDAO.selectFaq(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("faq-form.jsp");
		request.setAttribute("faq", existingFaq);
		dispatcher.forward(request, response);

	}

	private void insertFaq(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String category = request.getParameter("category");
		String problem = request.getParameter("problem");
		String solution = request.getParameter("solution");
		String author = request.getParameter("author");
		Faq newFaq = new Faq(category, problem, solution, author);
		faqDAO.insertFaq(newFaq);
		response.sendRedirect("faqlist");
	}

	private void updateFaq(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String category = request.getParameter("category");
		String problem = request.getParameter("problem");
		String solution = request.getParameter("solution");
		String author = request.getParameter("author");

		Faq fq = new Faq(id, category, problem, solution, author);
		faqDAO.updateFaq(fq);
		response.sendRedirect("faqlist");
	}

	private void deleteFaq(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		faqDAO.deleteFaq(id);
		response.sendRedirect("faqlist");

	}

}
