/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Nota;
import com.keyri.BD.ConexionAMYSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Keyri
 */
public class Notas {
    ConexionAMYSQL conexion = new ConexionAMYSQL();
    Connection con = conexion.getConecction();
    
    public ArrayList<Nota> ListadoNotas() {
        ArrayList<Nota> listado = null;

        try {
            listado = new ArrayList<Nota>();

            CallableStatement cb = conexion.prepareCall("{call SP_S_MOSTRARNOTAS}");
            ResultSet resultado = cb.executeQuery();

            while (resultado.next()) {
                //Llamar a el objeto de entidades.
                Nota n = new Nota();
                n.setNota(resultado.getString("Nota"));
                n.setNombreMateria(resultado.getString("NombreMateria"));
                n.setNombre(resultado.getString("Nombre"));
          
                listado.add(n);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return listado;

    }
}
