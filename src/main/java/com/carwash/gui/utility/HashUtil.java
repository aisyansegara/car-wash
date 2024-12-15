/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carwash.gui.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ASUS
 */
public class HashUtil {
    public static String hashPassword(String password) {
        try {
            // Mendapatkan instance SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Mengubah password menjadi array byte dan melakukan hash
            byte[] hash = digest.digest(password.getBytes());
            // Membentuk hasil hash menjadi format heksadesimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                // Mengonversi setiap byte menjadi string heksadesimal
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // Menambahkan 0 jika kurang dari 2 digit
                }
                hexString.append(hex);
            }
            return hexString.toString(); // Mengembalikan string hash
        } catch (NoSuchAlgorithmException e) {
            // Jika algoritma tidak ditemukan, lemparkan error
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
