/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Capricciosa;
import beans.Cheese;
import beans.Funghi;
import beans.Lieferadresse;
import beans.Margarita;
import beans.Oliven;
import beans.Pizza;
import beans.PizzaOhne;
import beans.PizzaZutaten;
import beans.Salami;
import beans.SalamiPizza;
import beans.Schinken;
import beans.Tomatos;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private ArrayList<PizzaZutaten> pizzaZutatenList = new ArrayList();
    private Margarita defaultPizza = new Margarita();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //DO not delete this line!!

        pizzaList.add(new Margarita());
        pizzaList.add(new SalamiPizza());
        pizzaList.add(new PizzaOhne());
        pizzaList.add(new Funghi());
        pizzaList.add(new Capricciosa());

        pizzaZutatenList.add(new Cheese(defaultPizza));
        pizzaZutatenList.add(new Tomatos(defaultPizza));
        pizzaZutatenList.add(new Salami(defaultPizza));
        pizzaZutatenList.add(new Schinken(defaultPizza));
        pizzaZutatenList.add(new Oliven(defaultPizza));
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
        this.getServletContext().setAttribute("pizzaZutatenList", pizzaZutatenList);
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
        this.getServletContext().setAttribute("pizzaZutatenList", pizzaZutatenList);
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
        HttpSession session = request.getSession();

        if (zureuck != null) {
            request.getRequestDispatcher("jsp" + File.separator + "PizzaAuswahl.jsp").forward(request, response);
        }
        if (bestellen != null) {
            for (Pizza pizza : pizzaList) {
                if (pizza.getPizzaName().equals(request.getParameter("selectedPizza"))) {
                    Pizza bestelltePizza = null;
                    switch (pizza.getPizzaName()) {
                        case "PizzaOhne":
                            bestelltePizza = new PizzaOhne();
                            break;
                        case "SalamiPizza":
                            bestelltePizza = new SalamiPizza();
                            break;
                        case "Margarita":
                            bestelltePizza = new Margarita();
                            break;
                        case "Funghi":
                            bestelltePizza = new Funghi();
                            break;
                        case "Capricciosa":
                            bestelltePizza = new Capricciosa();
                            break;
                        default:
                            bestelltePizza = new Margarita();
                            break;
                    }
                    for (PizzaZutaten pizzaZutaten : pizzaZutatenList) {
                        int bestellteZutatenNumber = Integer.parseInt(request.getParameter(pizzaZutaten.getZutatenName()));
                        for (int i = 0; i < bestellteZutatenNumber; i++) {
                            switch (pizzaZutaten.getZutatenName()) {
                                case "Salami":
                                    bestelltePizza = new Salami(bestelltePizza);
                                    break;
                                case "Tomato":
                                    bestelltePizza = new Tomatos(bestelltePizza);
                                    break;
                                case "Cheese":
                                    bestelltePizza = new Cheese(bestelltePizza);
                                    break;
                                case "Schinken":
                                    bestelltePizza = new Schinken(bestelltePizza);
                                    break;
                                case "Oliven":
                                    bestelltePizza = new Oliven(bestelltePizza);
                                    break;
                                default:
                                    bestelltePizza = new Tomatos(bestelltePizza);
                                    break;
                            }
                        }
                    }
                    session.setAttribute("bestelltePizza", bestelltePizza);
                }
            }
            session.setAttribute("lieferadresse", new Lieferadresse(strasse, plz));
            request.getRequestDispatcher("jsp" + File.separator + "PizzaBestellung.jsp").forward(request, response);

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
