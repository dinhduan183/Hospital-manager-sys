/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Databasehelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class doimatkhau extends javax.swing.JFrame {

    /**
     * Creates new form doimatkhau
     */
    
//    DoiMK_Model DoiMK = new DoiMK_Model();
    
    public doimatkhau() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
    }
    public String user;
    public void SetUs(String x) {
        this.Label_userrr.setText(x);
    }
    public String GetUs() {
        return this.Label_userrr.getText();
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        BTN_SAVEdoiMK = new javax.swing.JButton();
        taikhoann = new javax.swing.JLabel();
        matkhauu = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        Label_userrr = new javax.swing.JLabel();
        repass = new javax.swing.JPasswordField();
        matkhauu1 = new javax.swing.JLabel();
        matkhauu2 = new javax.swing.JLabel();
        newpass = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Đổi mật khẩu");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Đóng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, -1, -1));

        BTN_SAVEdoiMK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BTN_SAVEdoiMK.setText("Lưu");
        BTN_SAVEdoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SAVEdoiMKActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_SAVEdoiMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        taikhoann.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        taikhoann.setForeground(new java.awt.Color(0, 0, 204));
        taikhoann.setText("Tài khoản");
        getContentPane().add(taikhoann, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        matkhauu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        matkhauu.setForeground(new java.awt.Color(0, 0, 204));
        matkhauu.setText("Mật khẩu cũ");
        getContentPane().add(matkhauu, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));
        getContentPane().add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 117, -1));

        Label_userrr.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        Label_userrr.setForeground(new java.awt.Color(204, 0, 204));
        Label_userrr.setText("jLabel1");
        getContentPane().add(Label_userrr, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 111, -1));
        getContentPane().add(repass, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 117, -1));

        matkhauu1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        matkhauu1.setForeground(new java.awt.Color(0, 0, 204));
        matkhauu1.setText("Nhập lại MK");
        getContentPane().add(matkhauu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, -1));

        matkhauu2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        matkhauu2.setForeground(new java.awt.Color(0, 0, 204));
        matkhauu2.setText("Mật khẩu mới");
        getContentPane().add(matkhauu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));
        getContentPane().add(newpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 117, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setText("ĐỔI MẬT KHẨU");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/hospital-doimatkhau.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -6, 400, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_SAVEdoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SAVEdoiMKActionPerformed
            String np = newpass.getText();
            String rp = repass.getText();
            String pw = pass.getText();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
                Statement st = conn.createStatement();
                String sql = "SELECT * FROM bacsi WHERE username =? ";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,Label_userrr.getText());
                ResultSet rs = pstmt.executeQuery();
                rs.first();
                String password = rs.getString("password");
                    if((pw.equals(password)) && (np.equals(rp))){ //Nếu mật khẫu cũ đúng; mật khẩu mới nhập 2 lần giống nhau -> thành công
                        String sql2 = "UPDATE bacsi SET password=? WHERE username=? ";
                        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                        pstmt2.setString(1,np);
                        pstmt2.setString(2,Label_userrr.getText());
                        pstmt2.executeUpdate();
                        JOptionPane.showMessageDialog(this,"Cập nhật mật khẩu thành công!");
                    }else{
                        JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu thất bại!");
                    }
                conn.close();
            } catch(Exception e) {
                    JOptionPane.showMessageDialog(this,e);
            }
    }//GEN-LAST:event_BTN_SAVEdoiMKActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(doimatkhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(doimatkhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(doimatkhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(doimatkhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new doimatkhau().setVisible(true);
                new Login_View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_SAVEdoiMK;
    private javax.swing.JLabel Label_userrr;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel matkhauu;
    private javax.swing.JLabel matkhauu1;
    private javax.swing.JLabel matkhauu2;
    private javax.swing.JPasswordField newpass;
    private javax.swing.JPasswordField pass;
    private javax.swing.JPasswordField repass;
    private javax.swing.JLabel taikhoann;
    // End of variables declaration//GEN-END:variables
}
