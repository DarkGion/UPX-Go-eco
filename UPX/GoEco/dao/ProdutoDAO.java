package GoEco.dao;

import GoEco.entity.Produtos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection conn;

    public ProdutoDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    // INSERIR
    public void inserir(Produtos prod) {
        String sql = "INSERT INTO produtos (nome_prod, qtd_estoque, descricao, valor) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prod.getNome_prod());
            stmt.setInt(2, prod.getQtd_estoque());
            stmt.setString(3, prod.getDescricao());
            stmt.setDouble(4, prod.getValor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto: " + e.getMessage(), e);
        }
    }

    // LISTAR
    public List<Produtos> listar() {
        List<Produtos> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Produtos prod = new Produtos(
                        rs.getString("nome_prod"),
                        rs.getInt("qtd_estoque"),
                        rs.getString("descricao"),
                        rs.getDouble("valor")
                );
                produtos.add(prod);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage(), e);
        }
        return produtos;
    }

    // ATUALIZAR
    public void atualizar(Produtos prod, int id) {
        String sql = "UPDATE produtos SET nome_prod = ?, qtd_estoque = ?, descricao = ?, valor = ? WHERE produto_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prod.getNome_prod());
            stmt.setInt(2, prod.getQtd_estoque());
            stmt.setString(3, prod.getDescricao());
            stmt.setDouble(4, prod.getValor());
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
        }
    }

    // DELETAR
    public void deletar(int id) {
        String sql = "DELETE FROM produtos WHERE produto_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto: " + e.getMessage(), e);
        }
    }
    
    public int buscarUltimoId() {
    int id = 0;
    String sql = "SELECT MAX(prod_id) AS id FROM produtos";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
            id = rs.getInt("id");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return id;
}

public List<Produtos> listarTodos() {
    List<Produtos> lista = new ArrayList<>();
    String sql = "SELECT * FROM produtos";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Produtos p = new Produtos(
                rs.getInt("prod_id"),
                rs.getString("nome_prod"),
                rs.getInt("qtd_estoque"),
                rs.getString("descricao"),
                rs.getDouble("valor")
            );
            lista.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}
}
