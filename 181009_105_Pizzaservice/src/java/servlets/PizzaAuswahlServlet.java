/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Pizza;
import com.sun.imageio.plugins.common.I18N;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
            BufferedReader br = new BufferedReader(isr);

            String line = "";
            String seperator = ";";

            while ((line = br.readLine()) != null) {
                String[] pizzaDataStr = line.split(seperator);
                String name = pizzaDataStr[0];
                String fileName = pizzaDataStr[1];
                double price = Double.parseDouble(pizzaDataStr[2]);
                pizzaList.add(new Pizza(name, fileName, price));
            }
            br.close();
        } catch (Exception ex) {
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
        String lieferadresse = request.getParameter("lieferadresse");
        HttpSession session = request.getSession();

        if (zureuck != null) {
            request.getRequestDispatcher("jsp" + File.separator + "PizzaAuswahl.jsp").forward(request, response);
        }
        if (bestellen != null) {
            //TODO check Lieferadresse
            ArrayList<Pizza> bestelltePizzas = new ArrayList();
            for (Pizza p : pizzaList) {
                String pizzasStr = request.getParameter(p.getName());
                int pizzas = Integer.parseInt(pizzasStr);
                if (pizzas > 0) {
                    p.setMenge(pizzas);
                    bestelltePizzas.add(p);
                }
            }
            if (bestelltePizzas.size() > 0 && !lieferadresse.isEmpty()) {
                session.setAttribute("bestellungsListe", bestelltePizzas);
                session.setAttribute("lieferadresse", lieferadresse);
                request.getRequestDispatcher("jsp" + File.separator + "PizzaBestellung.jsp").forward(request, response);
            } else {
                //TODO auswahl error
            }
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
