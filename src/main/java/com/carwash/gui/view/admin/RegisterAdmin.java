/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.carwash.gui.view.admin;

import com.carwash.gui.repository.DatabaseConnection;
import com.carwash.gui.utility.HashUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class RegisterAdmin extends javax.swing.JFrame {

    /**
     * Creates new form RegisterAdmin
     */
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    
    public RegisterAdmin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsernameAdmin = new javax.swing.JTextField();
        txtNameAdmin = new javax.swing.JTextField();
        txtPasswordAdmin = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        registerBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register - Admin");

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 40)); // NOI18N
        jLabel1.setText("ADMIN REGISTRATION");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel2.setText("Password");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setText("Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 70, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setText("Username");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, -1, -1));

        txtUsernameAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameAdminActionPerformed(evt);
            }
        });
        jPanel1.add(txtUsernameAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 460, 30));

        txtNameAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameAdminActionPerformed(evt);
            }
        });
        jPanel1.add(txtNameAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 460, 30));

        txtPasswordAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordAdminActionPerformed(evt);
            }
        });
        jPanel1.add(txtPasswordAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 460, 30));

        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        jPanel1.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 100, 40));

        registerBtn.setText("Register");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });
        jPanel1.add(registerBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 370, 100, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameAdminActionPerformed

    private void txtPasswordAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordAdminActionPerformed

    private void txtNameAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameAdminActionPerformed

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        // TODO add your handling code here:
        String name = txtNameAdmin.getText();
        String username = txtUsernameAdmin.getText();
        String password = new String(txtPasswordAdmin.getPassword());
        
        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Tidak melanjutkan jika ada field yang kosong
        }
        
        String hashedPassword = HashUtil.hashPassword(password);
        
        try {
            conn = DatabaseConnection.getConnection();
            
            String checkUserSql = "SELECT COUNT(*) FROM tb_admin WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkUserSql);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            rs.next(); // Mengambil hasil query
            if (rs.getInt(1) > 0) {
                // Jika username sudah ada, tampilkan pesan error dan hentikan proses
                JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Keluar dari method tanpa melanjutkan registrasi
            } else {
                pst = conn.prepareStatement("INSERT INTO tb_admin (name, username, password) VALUES (?, ?, ?)");
                pst.setString(1, name);
                pst.setString(2, username);
                pst.setString(3, hashedPassword);
                pst.executeUpdate();

                LoginAdmin loginAdmin = new LoginAdmin();
                loginAdmin.setVisible(true);
                setVisible(false); 
            } 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Failed to register!", "Register Error", 1);
        }
    }//GEN-LAST:event_registerBtnActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
        LoginAdmin loginAdmin = new LoginAdmin();
        loginAdmin.setVisible(true);
    }//GEN-LAST:event_loginBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegisterAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JButton registerBtn;
    private javax.swing.JTextField txtNameAdmin;
    private javax.swing.JPasswordField txtPasswordAdmin;
    private javax.swing.JTextField txtUsernameAdmin;
    // End of variables declaration//GEN-END:variables
}
