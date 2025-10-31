package GoEco.dao;

import GoEco.entity.Tipo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoDAO {

    private Connection conn;

    public TipoDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    // INSERIR
    public void inserir(Tipo tipo) {
        String sql = "INSERT INTO tipo (tipo) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo.getTipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir tipo: " + e.getMessage(), e);
        }
    }

    // LISTAR
    public List<Tipo> listar() {
        List<Tipo> tipos = new ArrayList<>();
        String sql = "SELECT * FROM tipo";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Tipo tipo = new Tipo(
                    rs.getInt("tipo_id"),
                    rs.getString("tipo")
                );
                tipos.add(tipo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tipos: " + e.getMessage(), e);
        }
        return tipos;
    }

    // ATUALIZAR
    public void atualizar(Tipo tipo) {
        String sql = "UPDATE tipo SET tipo = ? WHERE tipo_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo.getTipo());
            stmt.setInt(2, tipo.getTipo_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tipo: " + e.getMessage(), e);
        }
    }

    // DELETAR
    public void deletar(int id) {
        String sql = "DELETE FROM tipo WHERE tipo_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar tipo: " + e.getMessage(), e);
        }
    }
    
    public int buscarUltimoId() {
    int id = 0;
    String sql = "SELECT MAX(tipo_id) AS id FROM tipo";
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

public Tipo buscarPorId(int id) {
    Tipo tipo = null;
    String sql = "SELECT * FROM tipo WHERE tipo_id= ?";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                tipo = new Tipo(rs.getInt("tipo_id"), rs.getString("tipo"));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return tipo;
}
}
