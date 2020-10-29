
package centro_de_estudios;

public class Administrador {
    
    private int codigo;
    
    private int anyos_trabajados;
    
    private String departamento;
    
    private String horario;
    
    Administrador (int codigo, int anyos_trabajados, String departamento, String horario) {        
        
        this.codigo = codigo;
        
        this.anyos_trabajados = anyos_trabajados;
        
        this.departamento = departamento;
        
        this.horario = horario;
    }

    public String getHorario() {return horario;}

    public int getCodigo() {return codigo;}

    public int getAnyos_trabajados() {return anyos_trabajados;}

    public String getDepartamento() {return departamento;}

    
}
