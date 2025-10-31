package GoEco.entity;

public class Usuarios {
    private String nome;
    private int contato_id;
    private int endereco_id;
    private int produtos_id;
    private int tipo_id;

    public Usuarios() {
    }

    public Usuarios(String nome, int contato_id, int endereco_id, int produtos_id, int tipo_id) {
        this.nome = nome;
        this.contato_id = contato_id;
        this.endereco_id = endereco_id;
        this.produtos_id = produtos_id;
        this.tipo_id = tipo_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getContato_id() {
        return contato_id;
    }

    public void setContato_id(int contato_id) {
        this.contato_id = contato_id;
    }

    public int getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(int endereco_id) {
        this.endereco_id = endereco_id;
    }

    public int getProdutos_id() {
        return produtos_id;
    }

    public void setProdutos_id(int produtos_id) {
        this.produtos_id = produtos_id;
    }

    public int getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    
    

    
    
    
}
