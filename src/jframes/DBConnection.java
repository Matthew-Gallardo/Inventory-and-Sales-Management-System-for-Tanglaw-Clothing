/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframes;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Administrator
 */
public class DBConnection {
    
 static  Connection con =null;
   public static Connection getConnection(){
   
    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tanglaw?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false", "root", "12345678");
        } catch (Exception e) {
            e.printStackTrace();
        }
    return con;
   }
         
        
}
