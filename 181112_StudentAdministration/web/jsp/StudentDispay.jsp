<%-- 
    Document   : StudentDispay
    Created on : 13.11.2018, 08:16:03
    Author     : Manuel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylaesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>Student Administration</title>
    </head>
    <body class="container">
        <h1>Student Administration</h1>
        <form method="POST" name="studentForm">
            <div>
                Select class:
                <select onchange="submit()" name="changeStudentKlasse">
                    <c:forEach var="studentKlasse" items="${studentKlassen}">
                        <option>${studentKlasse}</option>
                    </c:forEach>
                </select>
                <table border="0">
                    <c:forEach var="currentStudent" items="${filterdStudents}">
                    <tr>
                        <td><input type="radio" name="radioButtonStudents" value="${i=i+1}"></td>
                        <td>${i}</td>
                        <td>${currentStudent.getFullName()}</td>
                        <td>${currentStudent.getBirthdate().toString()}</td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            <input type="submit" value="Remove" name="remove">
        </form>
    </body>
</html>
