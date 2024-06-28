/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foxconn.System.Controller;


import Foxconn.System.EJB.EJBemployeeLocal;
import Foxconn.System.ENTITY.Employee;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Zacarias_Mercado
 */
public class SvEmployee extends HttpServlet {

    @EJB
    private EJBemployeeLocal ejbEmployee;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String actionBtn = request.getParameter("toDo");
        Employee employee = new Employee();

        switch (actionBtn) {
            case "Save":
                ArrayList<String> list = new ArrayList<>();

                try {
                    FileItemFactory file = new DiskFileItemFactory();
                    ServletFileUpload fileUpload = new ServletFileUpload(file);
                    List items = fileUpload.parseRequest(request);
                    for (int i = 0; i < items.size(); i++) {
                        FileItem fileItem = (FileItem) items.get(i);

                        if (!fileItem.isFormField()) {
                            File f = new File("C:\\images\\" + fileItem.getName());
                            fileItem.write(f);
                            //employee.setPicture(f.getAbsolutePath().getBytes());
                        } else {
                            list.add(fileItem.getString());
                        }
                    }

                    employee.setName(list.get(0));
                    ejbEmployee.ADD(employee);
                } catch (Exception e) {
                }
                
                request.getRequestDispatcher("index.xhtml").forward(request, response);

                break;
            default:
                throw new AssertionError();

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
