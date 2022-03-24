/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Usuario;
import com.keyri.BD.ConexionAMYSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Keyri
 */
public class UserLog {
    
    ConexionAMYSQL con = new ConexionAMYSQL();
    Connection conexion = con.getConecction();

    public ArrayList<Usuario> ListadoUsers(){
        ArrayList<Usuario> list = null;
        try {
            list = new ArrayList<Usuario>();

            CallableStatement cb = conexion.prepareCall("{call SP_I_USUARIO()}");
            ResultSet resultado = cb.executeQuery();

            while (resultado.next()) {
                
                Usuario user = new Usuario();
                user.setUsuario(resultado.getString("usuario"));
                user.setPassword(resultado.getString("Password"));
          

                list.add(user);
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return list;

    }
    
    public int ComprobarUser(Usuario u){
       int validar = 0;
       
        try{
          
            CallableStatement cb = conexion.prepareCall("{call SP_COMPROBAR(?,?)}");
            cb.setString("PUsuario",u.getUsuario());
            cb.setString("PPassword",u.getPassword());
            ResultSet resultado = cb.executeQuery();

            while(resultado.next()) {
              
              validar = resultado.getInt("idUsuario");
             
            }
        }catch(Exception ex){
           
           JOptionPane.showMessageDialog(null, ex.toString());
        }
       
        return validar;
    }
}
