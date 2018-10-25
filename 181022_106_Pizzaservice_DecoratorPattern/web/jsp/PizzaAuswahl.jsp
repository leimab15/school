<%-- 
    Document   : PizzaAuswahl
    Created on : 09.10.2018, 08:28:59
    Author     : Manuel
--%>

<%@page import="beans.PizzaZutaten"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>Pizza Bestellen</title>
        <style>
            :focus{
              outline:none;
            }
            .radio{
              -webkit-appearance:button;
              -moz-appearance:button;
              appearance:button;
              border:4px solid #ccc;
              border-top-color:#bbb;
              border-left-color:#bbb;
              background:#fff;
              width:25px;
              height:25px;
              border-radius:50%;
            }
            .radio:checked{
              border:10px solid #4099ff;
            }
        </style>
    </head>
    <body class="container"> 
        <div class="col-md-12">
            <h1>Pizza Bestellen Online</h1>
        </div>
        <form action="PizzaAuswahlServlet" method="POST">
            <table border="0" class="table table-striped" style="background: #b3d5f2">
                <%
                    ArrayList<Pizza> pizzaList = (ArrayList<Pizza>) this.getServletContext().getAttribute("pizzaList");
                    for (Pizza p : pizzaList) {
                %>
                <tr>
                    <td><img src="res/<%=p.getPathToPicture()%>" width="120px" height="80px"></td>
                    <td style="font-size: 26px" ><%=p.getPizzaName()%></td>
                    <td style="font-size: 18px"><%=p.getPrice()%> €</td>
                    <td><input required="required" class="radio" type="radio" name="selectedPizza" value="<%=p.getPizzaName()%>"><br></td>
                </tr>
                <% } %>
            </table>
            <br>
            <hr>
            <h2>Extra Zutaten:</h2>
            <table border="0" class="table table-striped" style="background: #b3d5f2">
                 <%
                    ArrayList<PizzaZutaten> pizzaZutatenList = (ArrayList<PizzaZutaten>) this.getServletContext().getAttribute("pizzaZutatenList");
                    for (PizzaZutaten pZutaten : pizzaZutatenList) {
                %>
                <tr>
                    <td style="font-size: 26px"><%=pZutaten.getZutatenName()%></td>
                    <td style="font-size: 18px" ><%=pZutaten.getZutatenPreis()%> €</td>
                    <td><input type="number" name="<%=pZutaten.getZutatenName()%>" min="0" max="10" value="0"></td>
                </tr>
                <% } %>
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
            <br>
            <br>
        </form>
    </body>
</html>
