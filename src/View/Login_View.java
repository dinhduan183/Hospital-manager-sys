
package View;

import Model.Login_Model;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author IamDuna
 */
public class Login_View extends javax.swing.JFrame {
    
    Login_Model LoginModel = new Login_Model();
        
    public Login_View() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_LoginForm = new javax.swing.JPanel();
        Btn_DangNhap = new javax.swing.JButton();
        taikhoann = new javax.swing.JLabel();
        matkhauu = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        pass = new javax.swing.JPasswordField();
        quanlybenhvien = new javax.swing.JLabel();
        Btn_Huy = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ảnh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý bệnh viện");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Panel_LoginForm.setBackground(new java.awt.Color(255, 255, 255));
        Panel_LoginForm.setPreferredSize(new java.awt.Dimension(630, 400));

        Btn_DangNhap.setBackground(new java.awt.Color(255, 255, 255));
        Btn_DangNhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Btn_DangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/login.png"))); // NOI18N
        Btn_DangNhap.setText("Đăng nhập");
        Btn_DangNhap.setBorderPainted(false);
        Btn_DangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_DangNhapActionPerformed(evt);
            }
        });

        taikhoann.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        taikhoann.setText("Tài khoản");

        matkhauu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        matkhauu.setText("Mật khẩu");

        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });

        quanlybenhvien.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        quanlybenhvien.setText("ĐĂNG NHẬP");

        Btn_Huy.setBackground(new java.awt.Color(255, 255, 255));
        Btn_Huy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Btn_Huy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cancel-2.png"))); // NOI18N
        Btn_Huy.setText("Hủy");
        Btn_Huy.setBorderPainted(false);
        Btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_HuyActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/icons8-account-64.png"))); // NOI18N

        ảnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/hospital.jpg"))); // NOI18N

        javax.swing.GroupLayout Panel_LoginFormLayout = new javax.swing.GroupLayout(Panel_LoginForm);
        Panel_LoginForm.setLayout(Panel_LoginFormLayout);
        Panel_LoginFormLayout.setHorizontalGroup(
            Panel_LoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_LoginFormLayout.createSequentialGroup()
                .addGroup(Panel_LoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_LoginFormLayout.createSequentialGroup()
                        .addContainerGap(23, Short.MAX_VALUE)
                        .addComponent(Btn_Huy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(Panel_LoginFormLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(Panel_LoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(taikhoann)
                            .addComponent(matkhauu))
                        .addGap(18, 18, 18)
                        .addGroup(Panel_LoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))
                    .addGroup(Panel_LoginFormLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(Panel_LoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_LoginFormLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1))
                            .addComponent(quanlybenhvien))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(ảnh))
        );
        Panel_LoginFormLayout.setVerticalGroup(
            Panel_LoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_LoginFormLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quanlybenhvien, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Panel_LoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(taikhoann)
                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_LoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matkhauu)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_LoginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_DangNhap)
                    .addComponent(Btn_Huy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_LoginFormLayout.createSequentialGroup()
                .addComponent(ảnh)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(Panel_LoginForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed

    }//GEN-LAST:event_userActionPerformed

    private void Btn_DangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_DangNhapActionPerformed
        String un = user.getText();
        String p = pass.getText();
        
        try {
            if( LoginModel.DangNhap(un, p) == false){
                JOptionPane.showMessageDialog(this,"Tài khoản không tồn tại!");
            }
            else{ //Đăng nhập thành công
                dispose();
                Trangchu hihi = new Trangchu();
                hihi.SetU(un);
                hihi.setVisible(true);
//                new Trangchu().setVisible(true);
//                JOptionPane.showMessageDialog(this,"Xin chào bác sĩ!");
            }
        } catch (Exception ex) {
            Logger.getLogger(Login_View.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }//GEN-LAST:event_Btn_DangNhapActionPerformed
    
    private void Btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_HuyActionPerformed
    dispose();
    }//GEN-LAST:event_Btn_HuyActionPerformed

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
            java.util.logging.Logger.getLogger(Login_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_DangNhap;
    private javax.swing.JButton Btn_Huy;
    private javax.swing.JPanel Panel_LoginForm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel matkhauu;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel quanlybenhvien;
    private javax.swing.JLabel taikhoann;
    private javax.swing.JTextField user;
    private javax.swing.JLabel ảnh;
    // End of variables declaration//GEN-END:variables
}
