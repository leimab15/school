/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Lieferadresse;
import beans.Pizza;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Manuel
 */
@WebServlet(name = "PizzaAuswahlServlet", urlPatterns = {"/PizzaAuswahlServlet"})
public class PizzaAuswahlServlet extends HttpServlet {

    private ArrayList<Pizza> pizzaList = new ArrayList();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //DO not delete this line!!
        try {
            String pathToPizzas = getServletContext().getRealPath("/res/pizzaData.csv");
            File f = new File(pathToPizzas);

            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            try (BufferedReader br = new BufferedReader(isr)) {
                String line = "";
                String seperator = ";";

                while ((line = br.readLine()) != null) {
                    String[] pizzaDataStr = line.split(seperator);
                    String name = pizzaDataStr[0];
                    String fileName = pizzaDataStr[1];
                    double price = Double.parseDouble(pizzaDataStr[2]);
                    pizzaList.add(new Pizza(name, fileName, price));
                }
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Ein Error beim Laden ist aufgetreten: " + ex);
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
        this.getServletContext().setAttribute("pizzaList", pizzaList);
        request.getRequestDispatcher("jsp" + File.separator + "PizzaAuswahl.jsp").forward(request, response);
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
        this.getServletContext().setAttribute("pizzaList", pizzaList);
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
        String bestellen = request.getParameter("bestellen");
        String zureuck = request.getParameter("zurueck");
        String strasse = request.getParameter("lieferadresse");
        String plz = request.getParameter("plz");
        String setLang = request.getParameter("lang");
        HttpSession session = request.getSession();

        if (zureuck != null) {
            request.getRequestDispatcher("jsp" + File.separator + "PizzaAuswahl.jsp").forward(request, response);
        }

        
        if (bestellen != null) {
            ArrayList<Pizza> bestelltePizzas = new ArrayList();
            pizzaList.forEach((p) -> {
                String pizzasStr = request.getParameter(p.getName());
                int pizzas = Integer.parseInt(pizzasStr);
                if (pizzas > 0) {
                    p.setMenge(pizzas);
                    bestelltePizzas.add(p);
                }
            });
            if (bestelltePizzas.size() > 0 && !strasse.isEmpty() && !plz.isEmpty()) {
                session.setAttribute("bestellungsListe", bestelltePizzas);
                session.setAttribute("lieferadresse", new Lieferadresse(strasse, plz));
                request.getRequestDispatcher("jsp" + File.separator + "PizzaBestellung.jsp").forward(request, response);
                request.setAttribute("errorMessage", "");
            } else {
                request.setAttribute("errorMessage", "Bitte mindestes 1 Pizza bestellen!");
            }
        }
        
        if (setLang != null) {
            Cookie lang = new Cookie("lang", setLang);
            lang.setMaxAge(-1);
            response.addCookie(lang);
            response.sendRedirect("jsp" + File.separator + "PizzaAuswahl.jsp");
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
