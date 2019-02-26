/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import bl.StudentBL;
import java.io.IOException;
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
@WebServlet(name = "StudentAdmin", urlPatterns = {"/StudentAdmin"})
public class StudentAdmin extends HttpServlet {

    private StudentBL studentBL = new StudentBL();
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String pathToStudentsCSV = getServletContext().getRealPath("/res/students.csv");
        try {
            getServletContext().setAttribute("students", studentBL.loadData(pathToStudentsCSV));
            getServletContext().setAttribute("studentKlassen", studentBL.getStudentKlassen());
        } catch (IOException ex) {
            System.out.println("Error beim einlesen: "+ex);
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
        request.getSession().setAttribute("filterdStudents", getServletContext().getAttribute("students"));
        request.getRequestDispatcher("jsp/StudentDispay.jsp").forward(request, response);
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
        
        String changeStudentKlasse = request.getParameter("changeStudentKlasse");
        String removeButton = request.getParameter("remove");
        
        if(removeButton != null)
        {
            request.getSession().setAttribute("filterdStudents",studentBL.removeStudent(Integer.parseInt(request.getParameter("radioButtonStudents"))));
            getServletContext().setAttribute("students", studentBL.getStudents());
        }
        if(changeStudentKlasse != null)
        {
            request.setAttribute("i", -1);
            request.getSession().setAttribute("filterdStudents", studentBL.getStudentFromClass(changeStudentKlasse));
            request.getSession().setAttribute("selectedKlass", changeStudentKlasse);
        }
        request.getRequestDispatcher("jsp/StudentDispay.jsp").forward(request, response);
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
