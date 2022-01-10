package Model;

import View.Trangchu;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Login_Model {
    public boolean DangNhap(String un, String p)throws Exception {
        try{
            Connection conn = (Connection) Databasehelper.openConnection();
            Statement st = (Statement) conn.createStatement();
            String sql = "SELECT * FROM bacsi";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                if(un.equals(username) && p.equals(password)){
                    return true;
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi kết nối!");
        } 
        return false;
    }
    
}
