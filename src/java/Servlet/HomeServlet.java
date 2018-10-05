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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
        prepareHomePage(request, response, 1);
        
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
        String desc = request.getParameter("LinkDescription");
        HttpSession session = request.getSession();
        int page = (Integer)session.getAttribute("pageNumber");
        if(desc.equalsIgnoreCase("Next")){
            page = page + 1;
        }else{
            page = page - 1; 
        }
        if(page==0){
            page = 1;
        }
        else if(page>(new DAOCategory().getNumberOfRecord()/5)){
            page--;
        }
        prepareHomePage(request, response, page);
    }
    
    private void prepareHomePage(HttpServletRequest request, HttpServletResponse response, int catePage) throws IOException, ServletException {
        DAOCategory catdao = new DAOCategory();
        List<Category> catlist = catdao.getCategory(catePage, 5);
        request.setAttribute("Catelist", catlist);
        BlogDAO blogdao = new BlogDAO();
        List<Blog> blogList = blogdao.getRecentBlogs(1);
        request.setAttribute("Bloglist", blogList);
        HttpSession session = request.getSession();
        session.setAttribute("pageNumber", catePage);
//        session.setAttribute("currentBlog", blogList.get(0));
//        Cookie[] cookies = request.getCookies();
        Cookie cookie = new Cookie("currentBlogID", blogList.get(0).getID()+"");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
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

}
