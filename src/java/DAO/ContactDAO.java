/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBContext.DBContext;
import entity.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ContactDAO {

    public ContactDAO() {
        try {
            Connection conn = new DBContext().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertContact(Contact contact) throws Exception{
        
            String query = "INSERT INTO [dbo].[Contact]\n"
                    + "           ([Name]\n"
                    + "           ,[Email]\n"
                    + "           ,[Phone]\n"
                    + "           ,[Company])\n"
                    + "           ,[Message])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?)";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getPhone());
            ps.setString(4, contact.getCompany());
            ps.setString(5, contact.getMessage());
            ps.executeUpdate();
            conn.close();
            
    }
    
}
