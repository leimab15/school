<%-- 
    Document   : StundentView
    Created on : 25.09.2018, 09:19:23
    Author     : Manuel
--%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlets.StudentController"%>
<%@page import="beans.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>
        <title>Student View</title>
    </head>
    <body class="container"> 
        <h1>Schülerliste</h1>
        <form action="StudentController" method="POST">
            <br>
            <table class="table table-striped" border="0">
                <tr>
                    <td>Filter:</td>
                    <%String filterString = request.getParameter("filter") != null ? request.getParameter("filter") : "";%>
                    <td><input type="text" name="filter" value="<%=filterString%>" /></td>
                </tr>
                <tr>
                    <td><input class="btn btn-primary" type="submit" name="add" value="Filter setzten" /></td>
                    <td><input class="btn btn-warning" type="submit" name="delete" value="Filter entfernen" /></td>
                </tr>
                <tr>
                    <td>Schüler auswählen:</td>
                    <td>
                        <select id="studentCB" name="studentCB" onchange="submit()">
                            <%
                                Student currentStudent = new Student("", "", "", LocalDate.now(), 0);
                                ArrayList<Student> students = new ArrayList();
                                students = (ArrayList<Student>) request.getAttribute("students");
                                
                                int i = 0;
                                int currentStudentID = 0;
                                if (request.getParameter("studentCB") != null) {
                                    currentStudentID = Integer.parseInt(request.getParameter("studentCB"));
                                    if (students.size() > 0 && currentStudentID < students.size()) {
                                        currentStudent = students.get(currentStudentID);
                                    }
                                }
                                if (students.size() > 0) {
                                    for (Student s : students) {
                            %>
                            <option value="<%=i%>" <%= i == currentStudentID ? "selected" : ""%>><%=s.getFirstname() + " " + s.getLastname()%></option>
                            <%
                                        i++;
                                    }
                                }
                            %>
                        </select>
                    </td>
                </tr>

            </table>
        </form>
        <br>
        <hr>
        <table border="0" class="table table-striped" style="background: #edd32a">
            <script type="text/javascript">
            </script>
            <tr>
                <td>Name:</td>
                <td><%=currentStudent.getFirstname() + " " + currentStudent.getLastname()%></td>
            </tr>
            <tr>
                <td>Katalognummer:</td>
                <td><%=currentStudent.getKn()%></td>
            </tr>
            <tr>
                <td>Klasse:</td>
                <td><%=currentStudent.getKlasse()%></td>
            </tr>
            <tr>
                <td>Geschlecht:</td>
                <td>-</td>
            </tr>
            <tr>
                <td>Geburtsdatum:</td>
                <td><%=currentStudent.getBirthdate().toString()%></td>
            </tr>
        </table>
    </body>
</html>
