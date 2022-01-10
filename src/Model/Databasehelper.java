/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class Databasehelper {
    public static Connection openConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String URL = "jdbc:mysql://localhost/QLBV";
        String us = "root";
        String pa = "";
        Connection conn = DriverManager.getConnection(URL,us,pa);
        return conn;
    }
}
