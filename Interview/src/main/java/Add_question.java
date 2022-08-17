

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Add_question
 */
@WebServlet("/Add_question")
public class Add_question extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_question() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session!=null&&session.getAttribute("aname")!=null){         
		String testid = request.getParameter("date");
        int date =Integer.parseInt(testid);
		String ques = request.getParameter("ques");
		String opt1 = request.getParameter("opt1");
		String opt2 = request.getParameter("opt2");
		String ans = request.getParameter("ans");
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?useSSL=false","root","cricket@25");
			PreparedStatement pst = con.prepareStatement("insert into questions(udate,question,opt1,opt2,answer) values(?,?,?,?,?)");
			pst.setInt(1, date);
			pst.setString(2, ques);
			pst.setString(3, opt1);
			pst.setString(4, opt2);
			pst.setString(5, ans);
			pst.executeUpdate();
			response.sendRedirect("start.jsp");
		} catch (Exception e) {
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			}
		}else {
			 response.sendRedirect("admin.jsp");
		}
		
	}

}
