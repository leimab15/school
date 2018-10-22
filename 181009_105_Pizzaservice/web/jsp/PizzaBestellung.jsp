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
            <table border="0" class="table table-striped" style="background: #b3d5f2">
                <thead>
                    <tr style="font-size: 26px;">
                        <td>Pizza</td>
                        <td>Pr.</td>
                        <td>Stk.</td>
                        <td>Ges.</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Pizza> pizzasBestellt = (ArrayList<Pizza>) session.getAttribute("bestellungsListe");
                        double summe = 0;
                        for (Pizza p : pizzasBestellt) {
                            summe += p.getMenge()*p.getPrice();
                    %>
                    <tr>
                        <td style="font-size: 22px;"><%=p.getName()%></td>
                        <td style="font-size: 18px"><%=p.getPrice()%> €</td>
                        <td style="font-size: 18px"><%=p.getMenge()%></td>
                        <td style="font-size: 22px"><%=p.getMenge()*p.getPrice()%> €</td>
                    </tr>
                    <% }%>
                    <tr>
                        <td style="font-size: 22px;" align="right" colspan="3">Summe: </td>
                        <td style="font-size: 28px;"><%=summe%> €</td>
                    </tr>
                </tbody>
            </table>
            <div class="col-md-6">
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
