package myproject;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;  // เพิ่ม import statement นี้

public class connetData {
	
    // ประกาศตัวแปร JDBC_URL, USER, และ PASSWORD
    private static String servername = "localhost";
    private static String username = "root";
    private static String dbname = "user_db";
    private static Integer portnumber = 3306;
    private static String password = "";
    
    public static Connection getConnection() {
        Connection cnx = null;
        MysqlDataSource datasource = new MysqlDataSource();
        
        datasource.setServerName(servername);
        datasource.setUser(username);
        datasource.setPassword(password);
        datasource.setDatabaseName(dbname);
        datasource.setPortNumber(portnumber);
        
         try {
             cnx = datasource.getConnection();
         } catch (SQLException ex) {
             Logger.getLogger(connetData.class.getName()).log(Level.SEVERE, null, ex);
             ex.printStackTrace();
         }
        
        return cnx;
    }
}

