<%-- 
    Document   : PizzaBestellung
    Created on : 09.10.2018, 08:29:50
    Author     : Manuel
--%>

<%@page import="beans.Lieferadresse"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Pizza"%>
<%@page import="beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>Ihre Bestellung</title>
    </head>
    <body class="container"> 
        <div class="col-md-12">
            <h1>Pizza Bestellen Online</h1>
        </div>
        <form action="PizzaAuswahlServlet" method="POST">
            <% Pizza bestelltePizza = (Pizza) session.getAttribute("bestelltePizza");%>
            <div class="col-md-6">
                <div>Bestellte Pizza: <%=bestelltePizza.getPizzaName()%></div>
                <div>Kosten: <%= bestelltePizza.getPrice()%> €</div>
                <br>
                <% Lieferadresse la = (Lieferadresse) session.getAttribute("lieferadresse");%>
                Straße:  
                <br>
                <p><%= la.getStrasse()%></p>
                <div></div>
                PLZ:
                <br>
                <p><%= la.getPlz()%></p>
                <br>
                <input class="btn btn-primary" type="submit" name="zurueck" value="Zurück" />
            </div>
        </form>
    </body>
</html>
