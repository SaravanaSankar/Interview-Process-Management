

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Storeresult
 */
@WebServlet("/Storeresult")
public class Storeresult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Storeresult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession ses = request.getSession(false);
		if(ses!=null&& ses.getAttribute("name")!=null) {
		String date = request.getParameter("date");
		 int wrong = 0, correct = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?useSSL=false","root","cricket@25");
			PreparedStatement pst=con.prepareStatement("select * from questions where udate =? ");
			pst.setString(1,date);
		    ResultSet rs=pst.executeQuery();
		   
		    while (rs.next()) {
		    String correct_option = rs.getString("answer");
		   //get current question..
		    String id = rs.getString("quesno");
		   //get user answer
		    String answers = request.getParameter("ans"+id);
		   //check if equalif
		   
		    if (answers.equals(correct_option)) {
		    correct++; //increment

		    } else {
		    wrong++; //increment
		   }
		  }
		  
		  out.println("Correct Answer are" + correct);
		  out.println("<br>");
		  out.println("Wrong Answer are" + wrong);
		  ses.invalidate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		}else {
			response.sendRedirect("user.html");
		}

		
	}

}
