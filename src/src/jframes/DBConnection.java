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
             con = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_de2491772d8372e?serverTimezone=UTC&useSSL=false ","bae18a12adcf9d","28316f48");
        } catch (Exception e) {
            e.printStackTrace();
        }
    return con;
   }
         
        
}
