
package centro_de_estudios;

public class Alumno {
    
    private int codigo;
    
    private int curso;
    
    private String carrera;
    
    private double nota;
    
    Alumno (int codigo, int curso, String carrera, double nota) {        
        
        this.codigo = codigo;
        
        this.curso = curso;
        
        this.carrera = carrera;
        
        this.nota = nota;
    }

    public int getCodigo() {return codigo;}

    public int getCurso() {return curso;}

    public String getCarrera() {return carrera;}

    public double getNota() {return nota;}
    
}
