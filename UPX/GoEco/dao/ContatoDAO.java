package GoEco.dao;

import GoEco.entity.Contato;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private Connection conn;

    public ContatoDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void inserir(Contato contato) {
        String sql = "INSERT INTO contato (nome_contato, email, telefone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir contato: " + e.getMessage(), e);
        }
    }

    public List<Contato> listar() {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT * FROM contato";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Contato contato = new Contato(
                    rs.getString("nome_contato"),
                    rs.getString("email"),
                    rs.getString("telefone")
                );
                contatos.add(contato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar contato: " + e.getMessage(), e);
        }
        return contatos;
    }

    public void atualizar(Contato contato, String emailAntigo) {
        String sql = "UPDATE contato SET nome = ?, email = ?, telefone = ? WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getTelefone());
            stmt.setString(4, emailAntigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar contato: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM endereco WHERE contato_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar contato: " + e.getMessage(), e);
        }
    }
    
    public int buscarUltimoId() {
    int id = 0;
    String sql = "SELECT MAX(contato_id) AS id FROM contato";
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

public Contato buscarPorId(int id) {
    Contato contato = null;
    String sql = "SELECT * FROM contato WHERE contato_id = ?";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                contato = new Contato(
                    
                    rs.getString("nome_contato"),
                    rs.getString("email"),
                    rs.getString("telefone")
                );
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return contato;
}

    
    
}
