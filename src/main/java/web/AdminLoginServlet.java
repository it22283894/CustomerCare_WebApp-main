package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminemail = request.getParameter("adminemail");
        String password = request.getParameter("password");
        

        // Database connection setup
        Connection con = null;
        
        try {         
            
            con = DBConnection.getConnection();
            
            String query = "SELECT * FROM admin WHERE adminemail=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, adminemail);
            ps.setString(2, password);        
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            	
                HttpSession session = request.getSession();
                session.setAttribute("adminemail", adminemail);
                response.sendRedirect("adminpanel.jsp"); 
            } else {
                // Invalid login, display an error message
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("adminlogin.jsp").forward(request, response);
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeConnection(con);
        }
    }
}
