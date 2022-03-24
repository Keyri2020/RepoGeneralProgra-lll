/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Estudiante;
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
public class Estudiantes {
    ArrayList<Estudiante> listado = null;
       ConexionAMYSQL con = new ConexionAMYSQL();
       
    Connection conexion = con.getConecction();
    public ArrayList <Estudiante> ListadoEstudiantes(){
       
        try {
            listado = new ArrayList<Estudiante>();
            CallableStatement cb = conexion.prepareCall("{call SP_S_ESTUDIANTE()}");
            ResultSet resultado = cb.executeQuery();
            
            while (resultado.next()){
                Estudiante es = new Estudiante();
                //llamar a el objeto de entidades
                es.setNombre(resultado.getString("Nombre"));
                es.setApellido(resultado.getString("Apellido"));
                listado.add(es);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return listado;
    }
    
    public void SaveStudents(Estudiante es){
        try {
            CallableStatement cb = conexion.prepareCall("{call SP_I_ESTUDIANTE(?,?)}");
            cb.setString("PNombre",es.getNombre());
            cb.setString("PApellido", es.getApellido());
            
            cb.execute();
            
            JOptionPane.showMessageDialog(null, "Estudiante agregado", "Mensaje sistema",1);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error " +ex.toString());
        }
    }
}
