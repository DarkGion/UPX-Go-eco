package GoEco.entity;

public class Produtos {

    // Atributos
    private int prod_id;
    private String nome_prod;
    private int qtd_estoque;
    private String descricao;
    private double valor;

    // Construtor
    public Produtos(int prod_id, String nome_prod, int qtd_estoque, String descricao, double valor) {
        this.prod_id = prod_id;
        this.nome_prod = nome_prod;
        this.qtd_estoque = qtd_estoque;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Produtos(String nome_prod, int qtd_estoque, String descricao, double valor) {
        this.nome_prod = nome_prod;
        this.qtd_estoque = qtd_estoque;
        this.descricao = descricao;
        this.valor = valor;
    }
    
    

    // Getters e Setters
    public String getNome_prod() {
        return nome_prod;
    }

    public void setNome_prod(String nome_prod) {
        this.nome_prod = nome_prod;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    // Método toString()
    @Override
    public String toString() {
        return "\nProduto{"
                + "nome_prod='" + nome_prod + '\''
                + ", qtd_estoque=" + qtd_estoque
                + ", descricao='" + descricao + '\''
                + ", valor=" + valor
                + '}';
    }

}
