package centro_de_estudios;

import java.util.Scanner;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;

import java.util.logging.Level;

import java.util.logging.Logger;

public class Consultas_centro  {
    
    public Scanner sc = new Scanner(System.in);
    
    public static final ArrayList<Integer> id_reciclados = new ArrayList<>(); 
    
    public void Dar_alta(Connection conn1, int clave_tabla){
        
        try {        
            
            Statement stmt = conn1.createStatement();          
            
            Persona persona = Datos_fiscales();          
            
            //Se le asigna un id auto-incremental.
            
            if (id_reciclados.size() == 0) {stmt.executeUpdate("INSERT INTO datos_fiscales VALUES "+"("+null+", '"+persona.getNombre()+"', "+"'"+persona.getApellidos()+"', "+"'"+persona.getNif()+"', "+persona.getEdad()+", "+persona.getPoblacion()+")");}
            
            else {stmt.executeUpdate("INSERT INTO datos_fiscales VALUES "+"("+id_reciclados.get(0)+", '"+persona.getNombre()+"', "+"'"+persona.getApellidos()+"', "+"'"+persona.getNif()+"', "+persona.getEdad()+", "+persona.getPoblacion()+")");}    
            
            //Preguntar datos de su poblacion si no existe en poblaciones.
            
            if (Comprobar_poblacion (conn1,persona.getPoblacion()) == false) { 
				
				//Si no existen los datos de esa poblacion se preguntan             
                
                Poblacion poblacion = Datos_poblacion(persona.getPoblacion());
                
                stmt.executeUpdate("INSERT INTO poblaciones VALUES ("+poblacion.getCodigo()+", '"+poblacion.getNombre()+"', "+poblacion.getCod_postal()+")");
            }
            
            if (clave_tabla == 01) {
				
				//Metodo dar alta admin
                
                if (id_reciclados.size()==0) {Dar_alta_admin(conn1, clave_tabla, Id_top(conn1));}//Con Id_top le mandamos el ultimo id introducido porque es autoincremental.    
                
                else {
                    
                    Dar_alta_admin(conn1, clave_tabla, id_reciclados.get(0)); //Se le manda el primer id de la lista de ids reciclados.
                    
                    id_reciclados.remove(0);} //Borramos el primer id una vez ha sido reciclado    
            
            } else {//Dar de alta en la tabla alumnos
				
				//Metodo dar alta alumno
                
                if (id_reciclados.size() == 0) {Dar_alta_alumno(conn1, clave_tabla, Id_top(conn1));}
                    
                else {
					
					Dar_alta_alumno (conn1, clave_tabla, id_reciclados.get(0));
                    
                    id_reciclados.remove(0);
                }    
            }                                                          
            
            stmt.close();
        
        } catch (SQLException ex) {
            
            System.out.println("Error: Al dar de alta");
            
            ex.printStackTrace();
        }
    }
    
    public void Dar_alta_admin (Connection conn1, int clave_tabla, int id_persona) {
        
        try {
        
            Statement stmt = conn1.createStatement();
            
            Administrador administrador = Datos_admin(conn1, clave_tabla);
                                 
            stmt.executeUpdate("INSERT INTO administracion VALUES " + " (" + administrador.getCodigo() + " , " + id_persona+", "+administrador.getAnyos_trabajados()+", "+"'"+administrador.getDepartamento()+"', "+"'"+administrador.getHorario()+"'"+")");
        
            System.out.println("Se ha dado de alta al administrador");
        
            stmt.close();
        
        } catch (SQLException ex) {
        
            System.out.println("Error: Al dar de alta administrador");
        
            ex.printStackTrace();
        }
    }
    
    public void Dar_alta_alumno (Connection conn1, int clave_tabla, int id_persona) {//Dar de alta un alumno nuevo
         
         try {
            
            Statement stmt = conn1.createStatement();
            
            Alumno alumno = Datos_alumno(conn1, clave_tabla);
                                               
            stmt.executeUpdate("INSERT INTO alumnos VALUES "+"("+alumno.getCodigo()+", "+id_persona+", "+alumno.getCurso()+", "+"'"+alumno.getCarrera()+"', "+alumno.getNota()+")");
            
            System.out.println("Se ha dado de alta al alumno");
            
            stmt.close();
        
        } catch (SQLException ex) {
        
            System.out.println("Error: Al dar de alta alumno");
        
            ex.printStackTrace();
        }             
    }
    
    public void Dar_baja (Connection conn1, int id_fiscal, int clave_tabla) {        
        
        try {
        
            Statement stmt = conn1.createStatement();
            
            if (clave_tabla == 01){
        
                stmt.executeUpdate("DELETE FROM administracion WHERE id_persona = " + id_fiscal);
        
                if (Comprobar_id_persona (conn1, 02, id_fiscal) == false) {//Si no esta en la otra tabla de alumnos se borra de datos fiscales.
        
                    stmt.executeUpdate("DELETE FROM datos_fiscales WHERE Id = " + id_fiscal);
        
                    Reciclar_id(id_fiscal);
        
                }
        
            } else {
        
                stmt.executeUpdate("DELETE FROM alumnos WHERE id_persona = " + id_fiscal);
        
                if (Comprobar_id_persona(conn1, 01, id_fiscal) == false) {//Si no esta en la otra tabla de administracion se borra de datos fiscales.
        
                    stmt.executeUpdate("DELETE FROM datos_fiscales WHERE Id="+id_fiscal);
        
                    Reciclar_id(id_fiscal);
        
                }
        
            }
        
            stmt.close();
        
        } catch (SQLException ex) {
        
            System.out.println("Error: Al dar de baja");
        
            ex.printStackTrace();
        
        }
    }
    
    public void Modificar(Connection conn1, int id_fiscal, int clave_tabla){
        
        try{
           
            Statement stmt = conn1.createStatement();
            
            System.out.println("Introduce los nuevos datos fiscales");
            
            System.out.print("Nombre:");
            
            String nombre = sc.nextLine();
            
            System.out.print("Apellidos:");
            
            String apellidos = sc.nextLine();          
            
            System.out.print("Edad:");
            
            int edad = sc.nextInt();
            
            System.out.print("Poblacion:");
            
            int poblacion = sc.nextInt();  
            
            stmt.executeUpdate("UPDATE datos_fiscales SET nombre="+"'"+nombre+"', "+"apellidos="+"'"+apellidos+"', "+"edad="+edad+", "+"poblacion="+poblacion+" WHERE Id="+id_fiscal);                             
            
            if (clave_tabla == 01) {
                
                System.out.println("Introduce los nuevos datos del administrador");                                                 
                
                System.out.print("Años trabajados:");
                
                int anyos = sc.nextInt();
                
                System.out.print("Departamento:");
                
                sc.nextLine();
                
                String departamento = sc.nextLine();
                
                System.out.print("Horario:");
                
                String horario = sc.nextLine();
                
                stmt.executeUpdate("UPDATE administracion SET "+"anyos_trabajados="+anyos+", "+"departamento="+"'"+departamento+"', "+"horario="+"'"+horario+"'"+" WHERE id_persona="+id_fiscal);  }     
                
            else {
                
                System.out.println("Introduce los nuevos datos del alumno");                                             
                
                System.out.print("Curso:");              
                
                int curso = sc.nextInt();
                
                System.out.print("Carrera:");
                
                sc.nextLine();
                
                String carrera = sc.nextLine();
                
                System.out.print("Nota Media:");
                
                double nota_media = sc.nextDouble();         
                
                stmt.executeUpdate("UPDATE alumnos SET "+"curso="+curso+", "+"carrera="+"'"+carrera+"', "+"nota_media="+nota_media+" WHERE id_persona="+id_fiscal);       
            
            }
                               
        } catch (SQLException ex) {
           
           System.out.println("Error: Al dar de baja");
           
           ex.printStackTrace(); 
        }
    }
    
    public String Consultar (Connection conn1, int id_fiscal, int clave_tabla) { //Mostramos sus datos fiscales, poblacion y datos de alumno o administrador
        
        String datos = "";
        
        int id_poblacion = 0;
       
        try {
            
            Statement stmt = conn1.createStatement();
            
            String query = "";
            
            query = "SELECT * FROM datos_fiscales WHERE Id="+id_fiscal;
            
            ResultSet rs = stmt.executeQuery(query);
            
            if (rs.next()) {
                
                datos = datos + "Id: " + rs.getInt(1) + ", Nombre: " + rs.getString(2) + ", Apellidos: " + rs.getString(3) + ", Nif: " + rs.getString(4) + ", Edad: " + rs.getInt(5) + ", Población: " + rs.getInt(6) + "\r\n";
                
                id_poblacion = rs.getInt(6);
            }
            
            query = "SELECT * FROM poblaciones WHERE codigo = " + id_poblacion;
            
            rs = stmt.executeQuery(query);
            
            if (rs.next()) {datos = datos + "Codigo Población: " + rs.getInt(1) + ", Nombre: " + rs.getString(2) + ", Codigo postal: " + rs.getInt(3) + "\r\n";}
            
            if (clave_tabla == 01) {
                
                query = "SELECT * FROM administracion WHERE id_persona = " + id_fiscal;
                
                rs = stmt.executeQuery(query);
                
                if (rs.next()) {datos = datos + "Codigo Administrador: " + rs.getInt(1) + ", Id Persona: " + rs.getInt(2) + ", Años trabajados: " + rs.getInt(3) + ", Departamento: " + rs.getString(4) + ", Horario: " + rs.getString(5);}
           
           else {
                
                query = "SELECT * FROM alumnos WHERE id_persona="+id_fiscal; 
                
                rs = stmt.executeQuery(query);
                
                if (rs.next()) {datos = datos + "Codigo Alumno: " + rs.getInt(1) + ", Id Persona: " + rs.getInt(2) + ", Curso: " + rs.getInt(3) + ", Carrera: " + rs.getString(4) + ", Nota Media: " + rs.getDouble(5);}
            }
            
            rs.close();
            
            stmt.close();           
        
        } 
 
        return datos;
        
        
        } catch (SQLException ex) {   Logger.getLogger(Consultas_centro.class.getName()).log(Level.SEVERE, null, ex);}
        
    
    return datos;

    }
            
    
    public int Id_top (Connection conn1) {
        
        int id = 0;
        
        try{              
        
            Statement stmt = conn1.createStatement();
        
            String query = "SELECT MAX(Id) FROM datos_fiscales";
        
            ResultSet rs = stmt.executeQuery(query);                    
        
            rs.next();
        
            id = rs.getInt(1);      
        
            rs.close();
        
            stmt.close();
        
        }catch(SQLException ex){
        
            System.out.println("Error: Al buscar el mayor id");
        
            ex.printStackTrace();
        
        }
        
        return id;
    }
    
    public boolean Comprobar_id_persona( Connection conn1, int clave_tabla, int id) { //Si existe el id en datos_fiscales se comprueba en alumnos o en administradores si existe o no.
        
        boolean existe = false;        
        
        try {
        
            Statement stmt = conn1.createStatement();
        
            String query = "";
        
            if (clave_tabla == 01) {query = "SELECT id_persona FROM administracion WHERE id_persona = " + id;}
        
            else {query = "SELECT id_persona FROM alumnos WHERE id_persona = " + id;}
        
            ResultSet rs = stmt.executeQuery(query);
        
            if (rs.next()) {existe = true;}                     
        
            rs.close();
        
            stmt.close();           
        
        } catch (SQLException ex) {
        
            System.out.println("Error: Al comprobar id_persona");
        
            ex.printStackTrace();
        
        }
        
        return existe;
    }
    
    public boolean Comprobar_nif (Connection conn1, String nif) { //Comprobar si el nif existe en datos_fiscales.
        
        boolean existe = false;        
        
        try {
        
            Statement stmt = conn1.createStatement();
        
            String query = "SELECT nif FROM datos_fiscales WHERE nif = " + " ' " + nif + " ' ";           
        
            ResultSet rs = stmt.executeQuery(query);
        
            if (rs.next()) {existe = true;}                     
            
            rs.close();
            
            stmt.close();
        
        } catch (SQLException ex) {
            
            System.out.println("Error: Al comprobar nif");
            
            ex.printStackTrace();
        
        }
        
        return existe;
    }
    
    public int Sacar_Id_fiscal (Connection conn1, String nif) { //Sacar el id a partir del nif.         
        
        int id_fiscal = 0;
        
        try {
        
            Statement stmt = conn1.createStatement();
        
            String query = "SELECT Id FROM datos_fiscales WHERE nif = " + " ' " + nif + " ' ";           
        
            ResultSet rs = stmt.executeQuery(query);
        
            rs.next();
        
            id_fiscal = rs.getInt(1);
        
            rs.close();
        
            stmt.close();
        
         } catch (SQLException ex) {
        
            System.out.println("Error: Al sacar id_fiscal");
        
            ex.printStackTrace();
        
        }
        
        return id_fiscal;
    }
    
    
    public boolean Comprobar_codigo (Connection conn1, int codigo, int clave_tabla) { //Comprobar codigo del alumno o administrador.
        
        boolean existe = false;
        
        try {        
        
            Statement stmt = conn1.createStatement();
        
            String query = "";
        
            if (clave_tabla == 01) {query = "SELECT codigo FROM administracion WHERE codigo = " + codigo;}
            
            else {query = "SELECT codigo FROM alumnos WHERE codigo = " + codigo;}
            
            ResultSet rs =  stmt.executeQuery(query);
            
            if (rs.next()) {existe = true;}    
            
            rs.close();
            
            stmt.close();
        
        } catch (SQLException ex) {
        
            System.out.println("Error: Al comprobar");
        
            ex.printStackTrace();
        
        }
        
        return existe;
    }    
    
    public boolean Comprobar_poblacion(Connection conn1,int id_poblacion){
        
        boolean existe = false;
        
         try {        
        
            Statement stmt = conn1.createStatement();
        
            String query = "SELECT codigo FROM poblaciones WHERE codigo = " + id_poblacion;          
        
            ResultSet rs =  stmt.executeQuery(query);
        
            if (rs.next()) {existe = true;}    
        
            rs.close();
        
            stmt.close();
        
        } catch(SQLException ex) {
        
            System.out.println("Error: Al comprobar");
        
            ex.printStackTrace();
        
        }
        
         return existe;
    }
    
    public Persona Datos_fiscales() {
        
        //Preguntar datos fiscales
        
        System.out.println("Introduce los datos fiscales");        
        
        System.out.print("Nombre:");
        
        String nombre = sc.nextLine();
        
        System.out.print("Apellidos:");
        
        String apellidos = sc.nextLine();
        
        System.out.print("Nif:");
        
        String nif = sc.nextLine();
        
        System.out.print("Edad:");
        
        int edad = sc.nextInt();
        
        System.out.print("Poblacion:");
        
        int poblacion = sc.nextInt();                          
            
        Persona persona = new Persona(nombre,apellidos,nif,edad,poblacion);
                       
        return persona;    
        
    } 
    
    public Administrador Datos_admin (Connection conn1, int clave_tabla){
        
        System.out.println("Introduce los datos del administrador");
        
        System.out.print("Codigo:");
        
        int codigo = sc.nextInt();
            
        while (Comprobar_codigo(conn1, codigo, clave_tabla)) {
        
            System.out.println("Ya existe un administrador con ese codigo");
        
            System.out.print("Codigo:");
        
            codigo = sc.nextInt();
        }
        
        System.out.print("Años trabajados:");
        
        int anyos = sc.nextInt();
        
        System.out.print("Departamento:");
        
        sc.nextLine();
        
        String departamento = sc.nextLine();
        
        System.out.print("Horario:");
        
        String horario = sc.nextLine();
        
        Administrador administrador = new Administrador(codigo, anyos, departamento, horario);
        
        return administrador;
    }
   
    public Alumno Datos_alumno(Connection conn1, int clave_tabla) {
        
        System.out.println("Introduce los datos del alumno");
        
        System.out.print("Codigo:");
        
        int codigo = sc.nextInt();
        
        while (Comprobar_codigo (conn1, codigo, clave_tabla)) { //Comprobar que no haya un codigo repetido.
            
            System.out.println("Ya existe un alumno con ese codigo.");
            
            System.out.print("Codigo:");
            
            codigo = sc.nextInt();
        }
        
        System.out.print("Curso:");              
        
        int curso = sc.nextInt();
        
        System.out.print("Carrera:");
        
        sc.nextLine();
        
        String carrera = sc.nextLine();
        
        System.out.print("Nota Media:");
        
        double nota_media = sc.nextDouble();
        
        Alumno alumno = new Alumno(codigo, curso, carrera, nota_media);
        
        return alumno;
    }
    
    public Poblacion Datos_poblacion(int codigo_poblacion) {
        
        System.out.println("Datos de población:");
        
        System.out.print("Nombre de población:");
        
        sc.nextLine();
        
        String nombre_poblacion = sc.nextLine();
        
        System.out.print("Codigo postal:");
        
        int codigo_postal = sc.nextInt();
        
        Poblacion poblacion = new Poblacion(codigo_poblacion,nombre_poblacion,codigo_postal);
        
        return poblacion;
    }
    
    public void Reciclar_id (int id_fiscal) {id_reciclados.add(id_fiscal);}

}
