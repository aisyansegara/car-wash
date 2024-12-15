/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carwash.gui.repository;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class DatabaseConnection {
    
    public static Connection conn;
    
    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/db_car_wash";
                String user = "root";
                String password = "";
                
                Driver driver = new Driver();
                DriverManager.registerDriver(driver);
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connection success!");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Connection failed!");
                ex.getMessage();
            }
        }
        return conn;
    }
}
