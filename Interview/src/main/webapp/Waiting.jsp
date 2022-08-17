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
HttpSession ses = request.getSession(false);
if(ses!=null&&ses.getAttribute("name")!=null){  
	String name=ses.getAttribute("name").toString();
%>
<%=name%> please wait for admin!!
<%}else{
	response.sendRedirect("user.html");
}%>
</body>
</html>