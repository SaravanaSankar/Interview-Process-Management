

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		if( session!=null&&session.getAttribute("aname")!=null){         
		PrintWriter out=response.getWriter();
		out.println("<h1>Test Attendees List</h1>");
		String date = request.getParameter("date");
		session.setAttribute("adate", date);
		List<Emp> list=EmpDao.getAllusersbydate(date);
		
		out.print("<table border='1' width='100%'");
		out.print("<tr><th>Id</th><th>Name</th><th>Date</th><th>Email</th><th>City</th><th>Status</th><th>Block</th><th>Allow</th></tr>");
		for(Emp e:list){
			out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td><td>"+e.getDate()+"</td><td>"+e.getEmail()+"</td><td>"+e.getCity()+"</td><td>"+e.getStatus()+"</td><td><a href='BlockServlet?id="+e.getId()+"'>Block</a></td><td><a href='AllowServlet?id="+e.getId()+"'>allow</a></td></tr>");
		}
		out.print("</table>");
		
		out.close();
	}else {
		response.sendRedirect("admin.jsp");
	}
		
	}
}
