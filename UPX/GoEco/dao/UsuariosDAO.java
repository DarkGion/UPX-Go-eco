package GoEco.dao;

import GoEco.entity.Usuarios;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {

    private Connection conn;

    public UsuariosDAO() {
        this.conn = new ConnectionFactory().getConnection();
    }

    // INSERIR
    public void inserir(Usuarios usuario) {
        String sql = "INSERT INTO usuarios (nome, contato_ref, endereco_ref, prod_ref, tipo_ref) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setInt(2, usuario.getContato_id());
            stmt.setInt(3, usuario.getEndereco_id());
            stmt.setInt(4, usuario.getProdutos_id());
            stmt.setInt(5, usuario.getTipo_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário: " + e.getMessage(), e);
        }
    }

    // LISTAR
    public List<Usuarios> listar() {
        List<Usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuarios usuario = new Usuarios(
                    rs.getString("nome"),
                    rs.getInt("contato_id"),
                    rs.getInt("endereco_id"),
                    rs.getInt("produtos_id"),
                    rs.getInt("tipo_id")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage(), e);
        }
        return usuarios;
    }

    // ATUALIZAR
    public void atualizar(Usuarios usuario, int id) {
        String sql = "UPDATE usuarios SET nome = ?, contato_id = ?, endereco_id = ?, produtos_id = ?, tipo_id = ? WHERE usuario_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setInt(2, usuario.getContato_id());
            stmt.setInt(3, usuario.getEndereco_id());
            stmt.setInt(4, usuario.getProdutos_id());
            stmt.setInt(5, usuario.getTipo_id());
            stmt.setInt(6, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
        }
    }

    // DELETAR
    public void deletar(int id) {
        String sql = "DELETE FROM usuarios WHERE usuario_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage(), e);
        }
    }
    
    public Usuarios buscarUltimoUsuario() {
    Usuarios u = null;
    String sql = "SELECT * FROM usuarios ORDER BY user_id DESC LIMIT 1";
    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
            u = new Usuarios(
                
                rs.getString("nome"),
                rs.getInt("endereco_ref"),
                rs.getInt("tipo_ref"),
                rs.getInt("prod_ref"),
                rs.getInt("contato_ref")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return u;
}

}
