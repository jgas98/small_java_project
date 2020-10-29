
package centro_de_estudios;

public class Poblacion {
    
    private int codigo;
    
    private String nombre;
    
    private int cod_postal;
    
    Poblacion (int codigo, String nombre, int cod_postal) {
       
        this.codigo = codigo;
       
        this.nombre = nombre;
       
        this.cod_postal = cod_postal;
    }

    public int getCodigo() {return codigo;}

    public String getNombre() {return nombre;}

    public int getCod_postal() {return cod_postal;}

}
