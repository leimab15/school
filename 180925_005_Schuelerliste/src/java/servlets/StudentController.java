/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Student;
import bl.Filter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Manuel
 */
@WebServlet(name = "StudentController", urlPatterns = {"/StudentController"})
public class StudentController extends HttpServlet {

    private ArrayList<Student> students = new ArrayList();
    private Filter f = new Filter();

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            super.init(config); //Do not remove this line!!!

            String pathToSchuelerData = getServletContext().getRealPath("/res/schuelerdaten.csv");
            File f = new File(pathToSchuelerData);
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            int kn = 1;
            String line = "";
            String seperator = ";";
            String klasseSt = "";
            
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] schuelerStrTokens = line.split(seperator);
                String klasse = schuelerStrTokens[0];
                String lastname = schuelerStrTokens[1];
                String firstname = schuelerStrTokens[2];
                LocalDate birthdate = LocalDate.parse(schuelerStrTokens[3]);
                if (!klasseSt.equals(klasse)) {
                    klasseSt = klasse;
                    kn = 1;
                }
                students.add(new Student(firstname, lastname, klasse, birthdate, kn));
                kn++;
            }
            br.close();
        } catch (Exception ex) {
            System.out.println("Fehler beim Lesen der Schueler: " + ex);
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
        request.getRequestDispatcher("StundentView.jsp").forward(request, response);
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
        request.setAttribute("students", students);
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
        String delete = request.getParameter("delete");

        if (delete != null) {
            request.setAttribute("students", students);
        } else {
            ArrayList<Student> filterList = new ArrayList();
            filterList.addAll(students);
            String filterString = request.getParameter("filter");
            filterList = f.filterStudents(students, filterString);
            request.setAttribute("students", filterList);
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

    public ArrayList<Student> getStudents() {
        return students;
    }
}
