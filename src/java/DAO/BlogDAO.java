/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBContext.DBContext;
import entity.Blog;
import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class BlogDAO {

    public BlogDAO() {
        try {
            Connection conn = new DBContext().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Blog loadBlogByID(int ID) {
        try {
            Connection conn;
            conn = new DBContext().getConnection();

            String query = "select * from Blog\n"
                    + "where ID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            List<Blog> a = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int blogID = rs.getInt("ID");
                String header = rs.getString("Header");
                int categoryID = rs.getInt("CategoryID");
                String content = rs.getString("[Content]");
                Timestamp date = rs.getTimestamp("Date");
                String img = rs.getString("Img");
                Blog b = new Blog(blogID, header, new DAOCategory().selectCategoryByID(categoryID), content, date, img);
                a.add(b);
            }
            rs.close();
            conn.close();
            return a.get(0);
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Blog> getRecentBlogs(int numberOfBlogs) {
        try {
            Connection conn;
            conn = new DBContext().getConnection();

            String query = "select top (?) * from Blog order by Date ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, numberOfBlogs);
            List<Blog> a = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int blogID = rs.getInt("ID");
                String header = rs.getString("Header");
                int categoryID = rs.getInt("CategoryID");
                String content = rs.getString("Content");
                Timestamp date = rs.getTimestamp("Date");
                String img = rs.getString("Img");
                Blog b = new Blog(blogID, header, new DAOCategory().selectCategoryByID(categoryID), content, date, img);
                a.add(b);
            }
            rs.close();
            conn.close();
            return a;
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Blog> selectByCategory(Category category, int pageNo, int resultPerPage) {
        try {
            Connection conn;
            conn = new DBContext().getConnection();

            String query = "Select *\n"
                    + "From \n"
                    + "(\n"
                    + "    Select \n"
                    + "      Row_Number() Over (Order By ID) As RowNum\n"
                    + "    , *\n"
                    + "    From Blog\n where CategoryID = ?"
                    + ") t2\n"
                    + "Where RowNum <= ? AND RowNum >= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(2, resultPerPage * pageNo);
            ps.setInt(3, resultPerPage * (pageNo - 1) + 1);
            ps.setInt(1, category.getID());
            List<Blog> a = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int blogID = rs.getInt("ID");
                String header = rs.getString("Header");
                int categoryID = rs.getInt("CategoryID");
                String content = rs.getString("Content");
                Timestamp date = rs.getTimestamp("Date");
                String img = rs.getString("Img");
                Blog b = new Blog(blogID, header, new DAOCategory().selectCategoryByID(categoryID), content, date, img);
                a.add(b);
            }
            rs.close();
            conn.close();
            return a;
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public int getNumberOfRecord(int CategoryID) {
        try {
            Connection conn;
            conn = new DBContext().getConnection();

            String query = "SELECT COUNT(ID)\n"
                    + "FROM Blog\n"
                    +"WHERE CategoryID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, CategoryID);
//            List<Category> a = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            rs.next();
            int x = rs.getInt(1);
//            while (rs.next()) {
//                int CategoryID = rs.getInt("CategoryID");
//                String CategoryName = rs.getString("CategoryName");
//                Category c = new Category(CategoryID, CategoryName);
//                a.add(c);
//            }
            rs.close();
            conn.close();
            return x;
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
