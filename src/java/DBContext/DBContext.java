/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class DBContext {

    /*USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTILPE SQL SERVER INSTANCE(s)*/
 /*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    public Connection getConnection() throws Exception {
//        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        String url = null;
        try{
            InitialContext initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup("java:/comp/env");
//            if(enviromentContext==null) url = "jdbc:sqlserver://localhost:1433;databaseName=OnlineQuiz";
            url =       (String) environmentContext.lookup("myConnectionURL");
            userID =    (String) environmentContext.lookup("databaseUser");
            password =  (String) environmentContext.lookup("databasePass");
        } catch (NamingException nex) {
            System.out.println("Can't read config");
        }
//        if(url==null && userID==null && password==null) url = "jdbc:sqlserver://localhost:1433;databaseName=OnlineQuiz";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        return DriverManager.getConnection(url, userID, password);
        return DriverManager.getConnection(url, "sa", "123456");
    }

    /*Insert your other code right after this comment*/
    Connection conn = null;

    public DBContext() {
        try {
            conn = getConnection();
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        Statement state;
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }
    /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/
//    private final String serverName = "localhost";
//    private final String dbName = "PRJ311";
//    private final String portNumber = "1433";
    private String userID;
    private String password;
}
