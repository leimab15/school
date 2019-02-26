<%-- 
    Document   : filmhome
    Created on : 07.01.2019, 08:38:12
    Author     : Manuel
--%>

<%@page import="beans.Film"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Filmdatenbank</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="icon" href="webProjekt/Bilder/kaindorfIcon.png">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            header {
                font-size: 20px;
            }
            thead, tbody { display: block; }

            tbody {
                height: 900px;       
                overflow-y: auto;    
                overflow-x: hidden;
            }
        </style>
    </head>
    <body>
        <header>
            <ul class="nav nav-pills">
                <li>Film:</li>
                <li><input type="text" name="film" value="" /></li>
                <li>Schauspieler:</li>
                <li><input type="text" name="film" value="" /></li>
                <li class="dropdown">
                   <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: black; font-size: 20px; padding: 0px">Genre
						<span class="caret"></span>
					</a>
                    <ul class="dropdown-menu">
                        <li>Test</li>  
                    </ul>
                </li>
                <li><input type="submit" value="Anzeige" name="anzeige"></li>
                <li> | <input type="checkbox" name="showBorrowedMovies"> Ausgeliehene Filme anzeigen | </li>
                <li><button>Login</button></li>
            </ul>
            <hr>
        </header>
        <table border="0" class="table-striped" style="background: #b3d5f2">
            <%
                    ArrayList<Film> films = (ArrayList<Film>) this.getServletContext().getAttribute("films");
                    for (Film film : films) {
                %>
                <tr>
                    <td style="font-size: 26px" ><%=film.getTitle()%></td>
                </tr>
                <% } %>
        </table>
    </body>
</html>
