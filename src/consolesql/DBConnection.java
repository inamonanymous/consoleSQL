/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consolesql;

import java.sql.Connection;
import java.sql.DriverManager;
import consolesql.ConsoleSQL;
/**
 *
 * @author Stephen Aguilar
 */
public class DBConnection {
    static final String DB_URL = "jdbc:mysql://localhost:3306/consoletable?zeroDateTimeBehavior=CONVERT_TO_NULL";
    static final String USER = "root";
    static final String PASS = "";
    
    public static Connection connectDB(){
        Connection conn = null;
        try{
            //register jdbc driver, not required for newer versions of jdk
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        }
        catch(Exception ex){
            System.out.println("There were errors while connecting to db");
            return null;
        }
        
    }
}
