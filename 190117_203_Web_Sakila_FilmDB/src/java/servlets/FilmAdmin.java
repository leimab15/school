/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Film;
import db.DB_Access;
import db.DB_ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.awt.AWTAccessor;

/**
 *
 * @author Manuel
 */
@WebServlet(name = "FilmAdmin", urlPatterns = {"/FilmAdmin"})
public class FilmAdmin extends HttpServlet {

    private DB_ConnectionPool connPool;
    private DB_Access access = new DB_Access();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connectToFilmDB();
    }

    private void connectToFilmDB() {
        try {
            connPool = DB_ConnectionPool.getInstance();
            System.out.println("Connected to DB!");
            try {
                Connection coon = connPool.getConnection();
                ArrayList<Film> films = access.getFilms();
                getServletContext().setAttribute("films", films);
                connPool.releaseConnection(coon);
            } catch (SQLException ex) {
                System.out.println("Error @ at requesting Films: " + ex.toString());
            }
        } catch (Exception ex) {
            System.out.println("Error @ Connectiong 2 DB.... Exiting: " + ex.toString());
            System.exit(405);
        }
    }

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

        request.getRequestDispatcher("jsp/Filmhome.jsp").forward(request, response);
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

        request.getRequestDispatcher("jsp/Filmhome.jsp").forward(request, response);
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
