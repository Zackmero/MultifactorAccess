package Foxconn.System.Controller;

import BEANS.Application.Validations;
import BEANS.BEANmain;
import BEANS.LoginValidation;
import BEANS.SESSION.SessionBeanMethods;
import Foxconn.System.EJB.EJBcredentialLocal;
import Foxconn.System.EJB.EJBemployeeLocal;
import Foxconn.System.ENTITY.Employee;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    @EJB
    private EJBemployeeLocal employeeEJB;

    @EJB
    private EJBcredentialLocal credentialEJB;

    LoginValidation lv = new LoginValidation();

    Validations v = new Validations();

    BEANmain bm = new BEANmain();

    SessionBeanMethods sbm = new SessionBeanMethods();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");

        System.out.println("SvLogin servlet user: " + username);
        System.out.println("SvLogin servlet pass: " + password);

        try {
            if (credentialEJB.credentialGetByUser(username, password)) {
                // Get the current employee
                Employee e = (Employee) employeeEJB.searchByEmployeeNumber(username);
                request.setAttribute("employeeSave", e);

                // Determine the result page based on user privileges
                if (e.getTbRoleId().getRole().equals("Administrador")) {
                    System.out.println("SvLogin index administrator: " + e.getName() + " " + e.getFirstLastName() + " " + e.getSecondLastName());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.xhtml");
                    dispatcher.forward(request, response);
                    // Stop further execution
                } else {
                    System.out.println("SvLogin index user: " + e.getName() + " " + e.getFirstLastName() + " " + e.getSecondLastName());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("indexUser.xhtml");
                    dispatcher.forward(request, response);
                    // Stop further execution
                }
            } else {
                // If authentication fails, redirect to login.xhtml again
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.xhtml");
                dispatcher.forward(request, response);
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace(); // Log the exception
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        //processLogin(request, response);
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
        processLogin(request, response);
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
