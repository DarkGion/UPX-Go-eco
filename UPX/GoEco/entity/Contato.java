package GoEco.entity;

public class Contato {

    private int contato_id;
    private String nome;
    private String email;
    private String telefone;

    public Contato() {
    }

    public Contato(String nome, String email, String telefone) {
        
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }



    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContato_id() {
        return contato_id;
    }

    public void setContato_id(int contato_id) {
        this.contato_id = contato_id;
    }

    @Override
    public String toString() {
        return "\n"
                + "Representate: " + nome + '\n'
                + "Email: " + email + '\n'
                + "Telefone: " + telefone;
               
    }

}
