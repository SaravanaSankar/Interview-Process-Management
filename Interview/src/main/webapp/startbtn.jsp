<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String name=null;
String date=null;
String email=null;
HttpSession ses = request.getSession(false);
if(ses.getAttribute("name")!=null && ses!= null){
	name =ses.getAttribute("name").toString();
	date =ses.getAttribute("date").toString();
	email =ses.getAttribute("email").toString();
	
%>
  				<form action="Testview" method="get">
		        <input type="hidden" name="name" value="<%=name%>">  
		        <input type="hidden" name="date" value="<%=date%>">  
		        <input type="hidden" name="email" value="<%=email%>">  
		     	<input type="submit" value="start-test">
		        </form> 
<%
}else{
response.sendRedirect("user.html");
}
%>

</body>
</html>