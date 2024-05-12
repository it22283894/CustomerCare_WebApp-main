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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        

        
        Connection con = null;
        
        try {         
            
            con = DBConnection.getConnection();
            
            String query = "SELECT * FROM userinfo WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);        
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            	
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                response.sendRedirect("homefaqlist"); 
            } else {
               
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeConnection(con);
        }
    }
}
