

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String date=request.getParameter("date");
		String email=request.getParameter("email");
		String city=request.getParameter("city");
		String status =new String("blocked");
		Emp e=new Emp();
		e.setName(name);
		e.setDate(date);
		e.setEmail(email);
		e.setCity(city);
		e.setStatus(status);
		
		int state=EmpDao.save(e);
		if(state>0){
			HttpSession ses = request.getSession();
			ses.setAttribute("name", name);
			ses.setAttribute("email", email);
			ses.setAttribute("date", date);
			response.sendRedirect("startbtn.jsp");
		    out.close();
		}
		out.close();
	}

}
