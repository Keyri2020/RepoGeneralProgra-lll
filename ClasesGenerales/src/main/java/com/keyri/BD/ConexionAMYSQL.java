/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.keyri.BD;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Keyri
 */
public class ConexionAMYSQL {
    
    private static Connection ConnectionBD = null;
    
    public Connection getConecction (){
        try {
            String url = "jdbc:mysql://localhost:3306/clase";
            String user = "keyri";
            String password = "root";
            ConnectionBD = DriverManager.getConnection(url,user,password);
            System.out.println("successful connect");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Eroor " +e.toString());
        }
        
        return ConnectionBD;
    }
    
    public CallableStatement prepareCall(String call_SP_S_MOSTRARNOTAS) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
