<%-- 
    Document   : CurrencyForm
    Created on : 25.09.2018, 08:20:22
    Author     : Manuel
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Currency Calculator</title>
    </head>
    <body>
        <form action="CurrencyCalcServelt" method="POST">
            <table border="0">
                <tr>
                    <% String amountStr = request.getParameter("amount") != null  ? 
                            request.getParameter("amount") : "100";  %>
                    <td><input type="text" name="amount" value="<%=amountStr%>" /></td>
                    <% String curStr = request.getParameter("baseCurrency") != null  ? 
                            request.getParameter("baseCurrency") : "EUR";  %>
                    <td>
                        <select name="baseCurrency">
                            <option value="EUR" <%= curStr.equals("EUR") ? "selected" : ""%>>Euro</option>
                            <option value="USD" <%= curStr.equals("USD") ? "selected" : ""%>>US-Dollar</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <% String result = request.getAttribute("result") == null ? "" : 
                            String.format("%1.2f", request.getAttribute("result")); %>
                    <td><input type="text" name="result" value="<%= result%>" /></td>
                    <td>
                        <% String targetStr = request.getParameter("targetCurrency") != null ? 
                                request.getParameter("baseCurrency") : "EUR";  %>
                        <select name="targetCurrency">
                            <option value="EUR" <%= targetStr.equals("EUR") ? "selected" : ""%>>Euro</option>
                            <option value="USD" <%= targetStr.equals("USD") ? "selected" : ""%>>US-Dollar</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Calculate" /></td>
                    <td></td>
                </tr>
            </table>
        </form>
        <% String errMsg = request.getAttribute("errMsg") == null ? "" : 
                request.getAttribute("errMsg").toString();%>
        <div style="color:red;"><b><i><%=errMsg%></i></b></div>
    </body>
</html>
