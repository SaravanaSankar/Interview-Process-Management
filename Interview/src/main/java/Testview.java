

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Testview
 */
@WebServlet("/Testview")
public class Testview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Testview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		RequestDispatcher rd= null;
		String name=null;
		String date=null;
		String email=null;
		String viewer=null;
		HttpSession ses = request.getSession(false);
		if(ses != null && ses.getAttribute("name")!=null){
			name =ses.getAttribute("name").toString();
			date =ses.getAttribute("date").toString();
			email =ses.getAttribute("email").toString();
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?useSSL=false", "root","cricket@25");
			PreparedStatement pst = con.prepareStatement("select status_value from users where name =? and date =? and email=?");
			pst.setString(1, name);
			pst.setString(2, date);
			pst.setString(3, email);
			ResultSet rs = pst.executeQuery();
			rs.next();
			viewer=rs.getString("status_value");
			if(viewer.equals("allowed")) {
				rd = request.getRequestDispatcher("view.jsp"); 
				rd.forward(request, response);
			}else {
				rd = request.getRequestDispatcher("Waiting.jsp"); 
				rd.forward(request, response);
			}
		} catch (Exception e) {
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		out.close();
		}else {
			response.sendRedirect("user.html");
		}
	}
	}

	
