/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.pa1.p1.datos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Jose David
 */
public class Conexion {
    
    Connection con;
    
    public static Connection Conexion() {
        try {
            String usuario = "uth";
            String contrasenia = "uth";
            String url = "jdbc:derby://localhost:1527/uth";
            System.out.println("conectado");
            return DriverManager.getConnection(url, usuario, contrasenia);
            
        } catch (Exception e) {
            System.out.println("Error getConexion: " + e.toString());
        }
        return null;
    }
    
    public Connection getConnection(){
        return Conexion();
    }
    
}
