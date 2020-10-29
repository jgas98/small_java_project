
package centro_de_estudios;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

public class Gestor_conexion {
    
    Connection conn1 = null;   
    
    public Gestor_conexion(){
        
        try {         
            
            String url1 = "jdbc:mysql://localhost:3306/centro_de_estudios";
            
            String user = "root";
            
            String password = "";
            
            conn1 = DriverManager.getConnection(url1, user, password);           
            
            if (conn1 != null) {System.out.println("Conectado a centro_de_estudios");}
            
        } catch (SQLException ex) {
            
            System.out.println("ERROR:La dirección no es válida o el usuario y clave");
            
            ex.printStackTrace();
        }
    }

    public Connection getConexion() {return conn1;}
    
     public void cerrar_Conexion () {
       
        try {        
 
            conn1.close();
            
        } catch (SQLException ex) {
            
            System.out.println("ERROR:al cerrar la conexión");
            
            ex.printStackTrace();
        
        }
    }
    
}
