package GoEco.entity;


public class Tipo {
    private int tipo_id;
    private String tipo;

    public Tipo() {
    }

    public Tipo(int tipo_id, String tipo) {
        this.tipo_id = tipo_id;
        this.tipo = tipo;
    }

    public int getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    
}
