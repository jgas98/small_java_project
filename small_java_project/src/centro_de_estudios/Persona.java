
package centro_de_estudios;

public class Persona {
    
    private String nombre;
    
    private String apellidos;
    
    private String nif;
    
    private int edad;
    
    private int poblacion;
    
    Persona (String nombre, String apellidos, String nif, int edad, int poblacion) {
        
        this.nombre = nombre;
        
        this.apellidos = apellidos;
        
        this.nif = nif;
        
        this.edad = edad;
        
        this.poblacion = poblacion;
    
    }

    public String getNombre() {return nombre;}

    public String getApellidos() {return apellidos;}

    public String getNif() {return nif;}

    public int getEdad() {return edad;}

    public int getPoblacion() {return poblacion;} 
    
}
