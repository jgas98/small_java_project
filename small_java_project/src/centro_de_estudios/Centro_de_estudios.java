
package centro_de_estudios;

import java.util.Scanner;

public class Centro_de_estudios {
    
    //se puede hacer otra para la entrada de datos
    
    public static void main(String[] args) {
       
        Scanner sc = new Scanner(System.in);
        
        Gestor_conexion gestor = new Gestor_conexion();
        
        Consultas_centro consulta = new Consultas_centro();  
       
        String nif = "";
        
        int id_fiscal = 0;
       
        int clave_admin = 01; 
        
        int clave_alumnos = 02;
        
        int numero;
       
        while (true){
           
            numero = menu(0);
           
            while (numero != 10 && numero != 20) {              
               
                if (numero > 10 && numero <= 14) { //Es admin
                    
                    switch (numero) {
                        
                        case 11:                          
                            
                            System.out.println("Introduce el nif del administrador (intro-Para añadir nuevo administrador):");//Usar 0 para dar de alta alquien nuevo al centro.
                            
                            nif = sc.nextLine();
                            
                            if (consulta.Comprobar_nif(gestor.getConexion(), nif)) {
                                
                                id_fiscal = consulta.Sacar_Id_fiscal(gestor.getConexion(), nif);
                                
                                if (consulta.Comprobar_id_persona (gestor.getConexion(), clave_admin, id_fiscal)) {System.out.println("Esa persona ya existe en administradores");}
                                
                                else {consulta.Dar_alta_admin(gestor.getConexion(), clave_admin, id_fiscal);}
                            
                            } else {
                                
                                if (!nif.equals("")) {System.out.println("El nif introducido no existe en datos fiscales");}
                                
                                else {consulta.Dar_alta(gestor.getConexion(), clave_admin);}
                                                 
                            }                         
                            
                            break;
                        
                        case 12:
                            
                            System.out.println("Introduce el nif del administrador a dar de baja");
                            
                            nif = sc.nextLine();
                            
                            if (consulta.Comprobar_nif(gestor.getConexion(), nif)) {
                            
                                id_fiscal = consulta.Sacar_Id_fiscal(gestor.getConexion(), nif);
                                
                                if (consulta.Comprobar_id_persona (gestor.getConexion(), clave_admin, id_fiscal)) {consulta.Dar_baja (gestor.getConexion(), id_fiscal, clave_admin);}
                                
                                else {System.out.println("Esa persona no existe en administradores");}
                                
                            } else {System.out.println("El nif introducido no existe en datos fiscales");}
                            
                            break;
                        
                        case 13:                          
                            
                            System.out.println("Introduce el nif del administrador a modificar");
                            
                            nif = sc.nextLine();
                            
                            if (consulta.Comprobar_nif(gestor.getConexion(), nif)){
                            
                                id_fiscal = consulta.Sacar_Id_fiscal(gestor.getConexion(), nif);
                                
                                if (consulta.Comprobar_id_persona (gestor.getConexion(), clave_admin, id_fiscal)) {consulta.Modificar(gestor.getConexion(), id_fiscal, clave_admin);}
                                
                                else {System.out.println("Esa persona no existe en administradores");}
                                
                            } else {System.out.println("El nif introducido no existe en datos fiscales");}
                            
                            break;
                        
                        case 14:
                            
                            System.out.println("Introduce el nif del administrador a consultar");
                            
                            nif = sc.nextLine();
                            
                            if (consulta.Comprobar_nif(gestor.getConexion(), nif)) {
                            
                                id_fiscal = consulta.Sacar_Id_fiscal(gestor.getConexion(), nif);
                                
                                if (consulta.Comprobar_id_persona (gestor.getConexion(), clave_admin, id_fiscal)) {System.out.println(consulta.Consultar(gestor.getConexion(), id_fiscal, clave_admin));}
                                
                                else {System.out.println("Esa persona no existe en administradores");}
                                
                            } else {System.out.println("El nif introducido no existe en datos fiscales");}
                            
                            break;    
                   }
                   
                    numero = menu(1); 
               }
               
               if (numero > 20 && numero <= 24) { //Es alumno
                    
                   switch (numero){
                        
                       case 21:                       
                            
                           System.out.println("Introduce el nif del alumno(intro-Para añadir nuevo alumno):");
                           
                           nif = sc.nextLine();
                           
                           if (consulta.Comprobar_nif (gestor.getConexion(), nif)) {
                                
                                id_fiscal = consulta.Sacar_Id_fiscal(gestor.getConexion(), nif);
                                
                                if (consulta.Comprobar_id_persona (gestor.getConexion(), clave_alumnos, id_fiscal)) {System.out.println("Esa persona ya existe en alumnos");}
                                
                                else {consulta.Dar_alta_alumno(gestor.getConexion(), clave_alumnos, id_fiscal);}    
                            
                            } else {
                                
                                if (!nif.equals("")) {System.out.println("El nif introducido no existe en datos fiscales");}
                                
                                else {consulta.Dar_alta(gestor.getConexion(), clave_alumnos);}
                               
                            }
                            
                            break;
                        
                        case 22:
                            
                            System.out.println("Introduce el nif del alumno a dar de baja");
                            
                            nif = sc.nextLine();
                            
                            if (consulta.Comprobar_nif(gestor.getConexion(), nif)) {
                            
                                id_fiscal = consulta.Sacar_Id_fiscal(gestor.getConexion(), nif);
                            
                                if (consulta.Comprobar_id_persona(gestor.getConexion(), clave_alumnos, id_fiscal)) {consulta.Dar_baja(gestor.getConexion(), id_fiscal, clave_alumnos);}
                            
                                else {System.out.println("Esa persona no existe en alumnos");}
                           
                            } else {System.out.println("El nif introducido no existe en datos fiscales");}
                            
                            break;
                        
                        case 23:
                        
                            System.out.println("Introduce el nif del alumno a modificar");
                        
                            nif = sc.nextLine();
                        
                            if (consulta.Comprobar_nif(gestor.getConexion(), nif)) {
                        
                                id_fiscal = consulta.Sacar_Id_fiscal(gestor.getConexion(), nif);
                        
                                if (consulta.Comprobar_id_persona(gestor.getConexion(), clave_alumnos, id_fiscal)) {consulta.Modificar(gestor.getConexion(), id_fiscal, clave_alumnos);}
                        
								else {System.out.println("Esa persona no existe en alumnos");}
                          
                            } else {System.out.println("El nif introducido no existe en datos fiscales");}
                          
                            break;       
                          
                        case 24:
                        
                            System.out.println("Introduce el nif del alumno a consultar");
                        
                            nif = sc.nextLine();
                        
                            if (consulta.Comprobar_nif(gestor.getConexion(), nif)) {
                        
                                id_fiscal = consulta.Sacar_Id_fiscal(gestor.getConexion(), nif);
                        
                                if (consulta.Comprobar_id_persona (gestor.getConexion(), clave_alumnos, id_fiscal)) { System.out.println(consulta.Consultar(gestor.getConexion(), id_fiscal, clave_alumnos));}
                        
                                else {System.out.println("No existe esa persona en alumnos");}
                        
                            } else {System.out.println("El nif introducido no existe en datos fiscales");}
                        
                        break;    
                   }
                   
                   numero = menu(2);  
               }
           }
       }                       
    }
    
    //devuelve el numero de opcion seleccionada teniendo en cuenta que administración comenzara por 1 y alumnos por 2
    
    //el parametro es para reutilizar la función si ya estamos en un submenu.
       
    private static int menu (int opcion) { //parametros validos: 0->todo el menu, 1 o 2 para admin o alumnos
         
        Scanner sc = new Scanner(System.in);
        
        int numero = 0;
        
        if (opcion == 0){
        
            System.out.println("1-Administración");
            
            System.out.println("2-Alumnos");
            
            System.out.print("Elije una opción: ");
            
            opcion = sc.nextInt();
         
        }
         
        if (opcion != 0) {
             
            System.out.println("0-Volver"); //Añadido para poder volver atras.
            
            System.out.println("1-Alta");
            
            System.out.println("2-Baja");
            
            System.out.println("3-Modificación");
            
            System.out.println("4-Consultar");
            
            System.out.print("Elije una opción: ");
            
            numero = sc.nextInt();
            
            numero = opcion * 10 + numero;
         
        }
         
        return numero;
    }
          
}
