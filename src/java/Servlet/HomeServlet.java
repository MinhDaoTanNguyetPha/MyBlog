/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.BlogDAO;
import DAO.DAOCategory;
import entity.Blog;
import entity.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
        BlogDAO blogdao = new BlogDAO();
        List<Blog> blogList = blogdao.getRecentBlogs(1);
        prepareHomePage(request, response, blogList.get(0).getID());

//        response.sendRedirect("/MyBlog/webpage/Homepage.jsp");
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
        int currentBlogID;
        try {
            currentBlogID = Integer.parseInt(request.getParameter("blogID"));
        } catch (NumberFormatException ex) {
            currentBlogID = new BlogDAO().getRecentBlogs(1).get(0).getID();
        }
        prepareHomePage(request, response, currentBlogID);
    }

    private void prepareHomePage(HttpServletRequest request, HttpServletResponse response, int currentBlogID) throws IOException, ServletException {
        DAOCategory catdao = new DAOCategory();
        List<Category> catlist = catdao.getCategory();
        request.setAttribute("Catelist", catlist);
        BlogDAO blogdao = new BlogDAO();
        Blog currentBlog = blogdao.loadBlogByID(currentBlogID);
        request.setAttribute("currentBlog", currentBlog);
        List<Blog> blogList = blogdao.getRecentBlogs(3);
        request.setAttribute("Bloglist", blogList);
        getServletContext().getRequestDispatcher("/webpage/Homepage.jsp").forward(request, response);
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
    //Người theo ánh trăng
}
