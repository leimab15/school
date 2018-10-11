/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import static bl.Rechner.calcFromEUR;
import static bl.Rechner.calcFromUSD;
import static bl.Rechner.calcFromYEN;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Manuel
 */
@WebServlet(name = "WaehrungsRechner", urlPatterns = {"/WaehrungsRechner"})
public class WaehrungsRechner extends HttpServlet {

     double erg = 0;
     boolean error = false;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>WährungsRechner</title>");            
            out.print("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>WährungsRechner</h1>");
            out.println("<br><hr><br>");
            request.getRequestDispatcher("html/waehrungsForm.html").include(request, response);
            if(!error)
            {
                out.println("Ergebnis: <label>"+erg+"</label>");
            }
            else
            {
                error = false;
                out.println("Ergebnis: <label> Falsche eingabe!</label>");   
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String betragString = request.getParameter("betrag");
        double betrag = 0;
        try {
            betrag = Double.parseDouble(betragString);
        } catch (Exception e) {
            error = true;
            betrag = 0;
        }
        
        String currency = request.getParameter("currency");
        String toCurrency = request.getParameter("toCurrency");
        
        switch(currency)
        {
            case("EUR"): erg=calcFromEUR(betrag,toCurrency); break; 
            case("YEN"): erg=calcFromYEN(betrag,toCurrency); break;
            case("USD"): erg=calcFromUSD(betrag,toCurrency); break;
        }
        
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
