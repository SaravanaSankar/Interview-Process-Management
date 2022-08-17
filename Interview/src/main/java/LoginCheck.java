
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String aname = request.getParameter("name");
		String apwd = request.getParameter("password");
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?useSSL=false", "root","cricket@25");
			PreparedStatement pst = con.prepareStatement("select * from admins where aname =? and passwd =?");
			pst.setString(1, aname);
			pst.setString(2, apwd);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				 HttpSession session=request.getSession();
				 session.setAttribute("aname", aname);
				 response.sendRedirect("start.jsp");  
			} else {
				response.sendRedirect("Error.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
