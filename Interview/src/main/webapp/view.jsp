<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd" > 
      <%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<html>
<head>
    <title>Test</title>
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires","0");
String date=null;
HttpSession ses = request.getSession(false);
if(ses!=null&&ses.getAttribute("name")!=null){  
	date = ses.getAttribute("date").toString();
    try
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?useSSL=false","root","cricket@25");
        PreparedStatement pst=con.prepareStatement("select * from questions where udate=? ORDER BY RAND()");
        pst.setString(1,date);
        ResultSet rs=pst.executeQuery();
        int i = 1;
        while(rs.next()){
        	int current_no =rs.getInt(1);
            String ques = rs.getString(3);
            String opt1 = rs.getString(4);
            String opt2 = rs.getString(5);
            String ans = rs.getString(6);
        %>                
        <%=i%>. <%=ques%><br/>
        <form action="Storeresult" action="get">
        <input type="radio" name="ans<%=current_no%>" value="<%=opt1%>"/>A.<%=opt1%><br/>
        <input type="radio" name="ans<%=current_no%>" value="<%=opt2%>"/>B.<%=opt2%><br/>
        <br/><br/>
        <%
            i++;
        }
    }catch(Exception ex){
        out.print("Exception Caught"+ex);
    }

   %>
    <input type="hidden" name="date" value="<%=date%>">
     <br/><br/>
     <input type="submit" name="submit" value="finish-test">
     </form>
<%}else{
	response.sendRedirect("user.html");
}%>
</body>
</html>

