<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd" > 
      <%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<html>
<head>
    <title>view questions</title>
</head>
<body>
<%
session = request.getSession(false);
if(session!=null&&session.getAttribute("aname")!=null){         
String date = request.getParameter("date");
    try
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?useSSL=false","root","cricket@25");
        PreparedStatement pst=con.prepareStatement("select * from questions where udate=?");
        pst.setString(1, date);
        ResultSet rs=pst.executeQuery();
    %><table border=1 align=center style="text-align:center">
      <thead>
          <tr>
             <th>Ques no</th>
             <th>date</th>
             <th>Question</th>
             <th>opt-1</th>
             <th>opt-2</th>
             <th>answer</th>
          </tr>
      </thead>
      <tbody>
        <%while(rs.next())
        {
            %>
            <tr>
                <td><%=rs.getInt("quesno") %></td>
                <td><%=rs.getInt("udate") %></td>
                <td><%=rs.getString("question") %></td>
                <td><%=rs.getString("opt1") %></td>
                <td><%=rs.getString("opt2") %></td>
                <td><%=rs.getString("answer") %></td>
            </tr>
            <%}%>
           </tbody>
        </table><br>
    <%}
    catch(Exception e){
        out.print(e.getMessage());%><br><%
    }
   }else{
        response.sendRedirect("admin.jsp");
    }
    %>
</body>
</html>

