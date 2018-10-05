/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBContext.DBContext;
import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOCategory {

    public DAOCategory() {
        try {
            Connection conn = new DBContext().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Category> getCategory() {
        try {
            return getCategory(1,getNumberOfRecord());
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private List<Category> getCategory(int pageNo, int resultPerPage) {
        try {
            Connection conn;
            conn = new DBContext().getConnection();

            String query = "Select *\n"
                    + "From \n"
                    + "(\n"
                    + "    Select \n"
                    + "      Row_Number() Over (Order By CategoryID) As RowNum\n"
                    + "    , *\n"
                    + "    From Category\n"
                    + ") t2\n"
                    + "Where RowNum <= ? and RowNum >=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, resultPerPage * pageNo);
            ps.setInt(2, resultPerPage * (pageNo - 1) + 1);
            List<Category> a = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                Category c = new Category(CategoryID, CategoryName);
                a.add(c);
            }
            rs.close();
            conn.close();
            return a;
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Category selectCategoryByID(int ID) {
        try {
            Connection conn;
            conn = new DBContext().getConnection();

            String query = "select * from Category \n"
                    + "where CategoryID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, ID);
            List<Category> a = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                Category c = new Category(CategoryID, CategoryName);
                a.add(c);
            }
            rs.close();
            conn.close();
            return a.get(0);
        } catch (Exception ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getNumberOfRecord() {
        try {
            Connection conn;
            conn = new DBContext().getConnection();

            String query = "SELECT COUNT(CategoryID)\n"
                    + "FROM Category";
            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setInt(1, ID);
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
