<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>admin actions</title>
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires","0");
session = request.getSession(false);
if(session.getAttribute("aname")!=null && session!=null){         
%>
<form action="ViewServlet" method="get">
date:<input type="text" name="date">
<input type="submit" value="start-test">
</form><br><br>
<h1>ADD QUESTION</h1>
<form action="Add_question" method="post">
date:<input type="number" name="date"><br>
question:<input type="text" name="ques"><br>
option-1:<input type="text" name="opt1"><br>
option-2:<input type="text" name="opt2"><br>
Answer:<input type="text" name="ans"><br>
<input type ="submit" value="add-question">
</form><br><br>
<h1>VIEW QUESTIONS</h1>
<form action="viewques.jsp">
date:<input type="text" name="date" id="date">
<input type ="submit" value="view-question">
</form><br><br>
<form action="Logout">
<input type="submit" value="Logout">
</form>
<%}else{
response.sendRedirect("admin.jsp");
}%>
</body>
</html>