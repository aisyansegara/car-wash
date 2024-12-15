/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carwash.gui.view.customer;

import com.carwash.gui.repository.DatabaseConnection;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author ASUS
 */


public class AddQueueDialog {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    
    protected void display(int user_id) {       
        String[] carTypes = {"", "Sedan", "SUV", "Pick-Up", "Super Car"};
        String[] categories = {"", "Interior Wash", "Exterior Wash", "Full Wash"};
            
        JComboBox<String> carTypesCombo = new JComboBox<>(carTypes);
        JTextField carName = new JTextField();
        JComboBox<String> categoriesCombo = new JComboBox<>(categories);
        
        JPanel panel = new JPanel(new GridLayout(3, 2));
        
        panel.add(new JLabel("Car Type"));
        panel.add(carTypesCombo);
        
        panel.add(new JLabel("Car name"));
        panel.add(carName);
        
        panel.add(new JLabel("Wash Type"));
        panel.add(categoriesCombo);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "Add Queue",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String c_carType = carTypesCombo.getSelectedItem().toString();
            String c_carName = carName.getText();
            String c_category = categoriesCombo.getSelectedItem().toString();
            
            if (c_carType.equals("") || c_carName.isEmpty() || c_category.equals("")) {
                System.out.println("All Fields are required!");
                return;
            }
            
            try {
                conn = DatabaseConnection.getConnection();
                
                // Query untuk mendapatkan car_id berdasarkan car_type
                String carIdSql = "SELECT car_id, cost FROM tb_cars WHERE type = ?";
                PreparedStatement pstCar = conn.prepareStatement(carIdSql);
                pstCar.setString(1, c_carType);
                ResultSet rsCar = pstCar.executeQuery();

                int carId = -1;
                int carCost = 0;
                if (rsCar.next()) {
                    carId = rsCar.getInt("car_id");
                    carCost = rsCar.getInt("cost");
                }

                if (carId == -1) {
                    System.out.println("Car type not found!");
                    return;
                }

                // Query untuk mendapatkan category_id berdasarkan category_name
                String categoryIdSql = "SELECT category_id, price FROM tb_categories WHERE category = ?";
                PreparedStatement pstCategory = conn.prepareStatement(categoryIdSql);
                pstCategory.setString(1, c_category);
                ResultSet rsCategory = pstCategory.executeQuery();

                int categoryId = -1;
                int categoryPrice = 0;
                if (rsCategory.next()) {
                    categoryId = rsCategory.getInt("category_id");
                    categoryPrice = rsCategory.getInt("price");
                }

                if (categoryId == -1) {
                    System.out.println("Category not found!");
                    return;
                }
                
                int total = carCost + categoryPrice;

                // Insert data ke tb_transactions
                String insertTransactionSql = "INSERT INTO tb_transactions (customer_id, car_id, category_id, total, status) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstInsert = conn.prepareStatement(insertTransactionSql);
                pstInsert.setInt(1, user_id); // Menggunakan c_name sebagai customer_id (pastikan validasi input)
                pstInsert.setInt(2, carId);
                pstInsert.setInt(3, categoryId);
                pstInsert.setInt(4, total); // Total dapat diisi sesuai kebutuhan
                pstInsert.setString(5, "In queue"); // Status dapat diubah sesuai alur

                int rowsInserted = pstInsert.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Transaction added successfully!");
                } else {
                    System.out.println("Gagal ditambahkan");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cancelled");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            private int user_id;

            @Override
            public void run() {
                AddQueueDialog addQueueDialog = new AddQueueDialog();
                addQueueDialog.display(user_id);
            }
        });
    }
}
