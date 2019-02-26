/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelts;

import beans.Konto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.security.pkcs11.wrapper.Functions;

/**
 *
 * @author Manuel
 */
@WebServlet(name = "Kontodaten", urlPatterns = {"/Kontodaten"})
public class Kontodaten extends HttpServlet {

    private ArrayList<Konto> kontos = new ArrayList();
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        FileInputStream ifs = null;
        try {
            super.init(config);
            String realPathToKondodataFile = getServletContext().getRealPath("/res/Kontodaten.csv");
            System.out.println(realPathToKondodataFile);
            File fileKondodata = new File(realPathToKondodataFile);
            
            FileInputStream fis = new FileInputStream(fileKondodata);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            
            BufferedReader br = new BufferedReader(isr);
            
            String line = "";
            String seperator = ",";
            while(( line = br.readLine()) != null)
            {
                String[] tokens = line.split(seperator);
                String namenSplit[] = tokens[3].split(" ");
                
                Konto k = new Konto(tokens[0], Double.parseDouble(tokens[2]), Long.parseLong(tokens[1]), namenSplit[1], namenSplit[0], Integer.parseInt(tokens[4]), tokens[5]);
                kontos.add(k);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kontodaten.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Kontodaten.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kontodaten.class.getName()).log(Level.SEVERE, null, ex);
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
        
        this.getServletContext().setAttribute("kontos", kontos);
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
