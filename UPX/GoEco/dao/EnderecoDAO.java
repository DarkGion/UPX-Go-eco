package GoEco.dao;

import GoEco.entity.Endereco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    private Connection conn;

    public EnderecoDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public void inserir(Endereco ender) {
        String sql = "INSERT INTO endereco(logradouro, numero,complemento,bairro,cidade,estado,cep) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ender.getLogradouro());
            stmt.setString(2, ender.getNumero());
            stmt.setString(3, ender.getComplemento());
            stmt.setString(4, ender.getBairro());
            stmt.setString(5, ender.getCidade());
            stmt.setString(6, ender.getEstado());
            stmt.setString(7, ender.getCep());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir contato: " + e.getMessage(), e);
        }
    }

    public List<Endereco> listar() {
        List<Endereco> endere = new ArrayList<>();
        String sql = "SELECT * FROM endereco";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Endereco ender = new Endereco(
                    rs.getString("logradouro"),
                    rs.getString("numero"),
                    rs.getString("complemento"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("cep")    
                );
                endere.add(ender);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar endereço: " + e.getMessage(), e);
        }
        return endere;
    }

    public void atualizar(Endereco endereco, String emailAntigo) {
        String sql = "UPDATE contato SET nome = ?, email = ?, telefone = ? WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, endereco.getLogradouro());
            stmt.setString(2, endereco.getNumero());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, emailAntigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar contato: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM endereco WHERE endereco_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar contato: " + e.getMessage(), e);
        }
    }
    
    public int buscarUltimoId() {
    int id = 0;
    String sql = "SELECT MAX(endereco_id) AS id FROM endereco";
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

public Endereco buscarPorId(int id) {
    Endereco end = null;
    String sql = "SELECT * FROM endereco WHERE endereco_id = ?";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                end = new Endereco(
                    rs.getInt("endereco_id"),
                    rs.getString("logradouro"),
                    rs.getString("numero"),
                    rs.getString("complemento"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("cep")
                );
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return end;
}



    
    
}
