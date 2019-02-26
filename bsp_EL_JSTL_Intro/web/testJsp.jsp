<%-- 
    Document   : testJsp
    Created on : 27.11.2018, 08:13:01
    Author     : Manuel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Some LE and JSTL tests:</h1>
        <!-- EL Ausdrücke-->
        Sekunden pro Tag: ${60*60*24} <br>
        Wert von Pi: ${Math.PI} <br> 
        Wurzel aus 8: ${Math.sqrt(8)} <br>
        Heutiges Datum: ${LocalDate.now()} <br>
        Mein Milchschake: ${requestScope.shake1} <br>
        Mein Milchschake: ${shake1} <br>
            Name: ${shake1.name} <br>
            Price: ${shake1.price} <br>
        <br>
        <!-- JSTL Ausdrücke-->
        <c:forEach var="shake" items="${shakeList}">
            ${shake} <br>
        </c:forEach>
            <br>
            <table border="1">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Größe</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="milkshake" items="${shakeList}">
                    <tr>
                        <td>${milkshake.name}</td>
                        <td>${milkshake.size}</td>
                        <td>€ ${milkshake.price}</td>
                    </tr>
                    </c:forEach>
                 
                </tbody>
            </table>
        <br>
    </body>
</html>
