<%-- 
    Document   : PizzaAuswahl
    Created on : 09.10.2018, 08:28:59
    Author     : Manuel
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>Pizza Bestellen</title>
    </head>
    <body class="container"> 
        <div class="col-md-12">
            <h1>Pizza Bestellen Online</h1>
        </div>
        <form action="PizzaAuswahlServlet" id="PizzaAuswahlServlet" method="POST">
            <div class="container">
                <select name="lang" form="PizzaAuswahlServlet" onchange="this.form.submit()">
                    <%
                        Cookie[] cookies = request.getCookies();
                        boolean hasLangCookie = false;
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("lang")) {
                                hasLangCookie = true;%> 
                                        <option value="de" <%=cookie.getValue().equals("de") ? "selected" : ""%>>Deutsch</option>
                                        <option value="en" <%=cookie.getValue().equals("en") ? "selected" : ""%>>Englisch</option>
                                    <%
                                    }
                                }
                             }
                        if(!hasLangCookie)
                        {
                        %>
                            <option value="de">Deutsch</option>
                            <option value="en">Englisch</option>
                        <%
                        }
                    %>
                </select>
            </div>
            <table border="0" class="table table-striped" style="background: #b3d5f2">
                <%
                    ArrayList<Pizza> pizzaList = (ArrayList<Pizza>) this.getServletContext().getAttribute("pizzaList");
                    for (Pizza p : pizzaList) {
                %>
                <tr>
                    <td><img src="res/<%=p.getPictureName()%>" width="120px" height="80px"></td>
                    <td style="font-size: 26px" ><%=p.getName()%></td>
                    <td style="font-size: 18px"><%=p.getPrice()%> €</td>
                    <td><input type="number" name="<%=p.getName()%>" min="0" max="10" value="0"></td>
                </tr>
                <% }%>
            </table>
            <div class="col-md-6">
                Lieferadresse:
                <br>
                <input type="text" name="lieferadresse" required="required" value="" />
                <br>
                PLZ:
                <br>
                <input type="text" name="plz" required="required" value="" />
                <br>
                <br>
                <input class="btn btn-primary" type="submit" name="bestellen" value="Bestellen" />
                <br>
                <div  style="color:red; font-size: 20px;"><%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : ""%></div>
            </div>
        </form>
    </body>
</html>
