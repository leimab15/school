<%-- 
    Document   : somejsp
    Created on : 06.11.2018, 08:17:20
    Author     : Manuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cookies Found:</h1>
        <%
        Cookie[] cookies = request.getCookies();
            if(cookies != null)
            {
                for (Cookie c : cookies)
                {
                out.print("Name: "+c.getName()+"<br>");
                out.print("Values: "+c.getValue()+"<br>");
                out.print("Max Age: "+c.getMaxAge()+"<br>");
                }
            }
            else
            {
                out.println("No Cookies found!");
            }
        %>
    </body>
</html>
