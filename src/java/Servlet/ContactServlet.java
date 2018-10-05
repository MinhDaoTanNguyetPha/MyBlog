/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.ContactDAO;
import DAO.DAOCategory;
import entity.Category;
import entity.Contact;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class ContactServlet extends HttpServlet {

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
            out.println("<title>Servlet ContactServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContactServlet at " + request.getContextPath() + "</h1>");
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
        DAOCategory catdao = new DAOCategory();
        List<Category> catlist = catdao.getCategory();
        request.setAttribute("Catelist", catlist);
        getServletContext().getRequestDispatcher("/webpage/Contact.jsp").forward(request, response);
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
        DAOCategory catdao = new DAOCategory();
        List<Category> catlist = catdao.getCategory();
        request.setAttribute("Catelist", catlist);
        Contact submittedContact = verifyContact(request);
        ContactDAO conDAO = new ContactDAO();
        if(submittedContact!=null) {
            try {
                conDAO.insertContact(submittedContact);
            } catch (Exception ex) {
                request.setAttribute("verifyingFailed", "Exception occurs");
                Logger.getLogger(ContactServlet.class.getName()).log(Level.SEVERE, null, ex);
                getServletContext().getRequestDispatcher("/webpage/Contact.jsp").forward(request, response);
            }
            getServletContext().getRequestDispatcher("/webpage/ContactSuccess.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/webpage/Contact.jsp").forward(request, response);
        }
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

    private Contact verifyContact(HttpServletRequest request) {
//        Contact tmp = null;
//        tmp = new Contact(0, name, email, phone, company, Message);
        String name = request.getParameter("name");
        if(name==null) {
            request.setAttribute("verifyingFailed", "Name can't be empty");
            return null;
        }
        String email = request.getParameter("email");
        if(email==null) {
            request.setAttribute("verifyingFailed", "Email can't be empty");
            return null;
        } else {
            if(email.split("@").length!=2) {
                request.setAttribute("verifyingFailed", "Email is in wrong format");
                return null;
            }
        }
        String phone = request.getParameter("phone");
        if(phone==null) {
            request.setAttribute("verifyingFailed", "Phone can't be empty");
            return null;
        }
        String company = request.getParameter("company");
        if(company==null) {
            request.setAttribute("verifyingFailed", "Company can't be empty");
            return null;
        }
        String message = request.getParameter("message");
        if(message==null) {
            request.setAttribute("verifyingFailed", "Message can't be empty");
            return null;
        }
        request.setAttribute("verifyingFailed", "");
        return new Contact(0, name, email, phone, company, message);
    }
}
