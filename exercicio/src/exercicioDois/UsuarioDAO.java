package exercicioDois;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection conexao;

    public boolean conectar() {
        String url = "jdbc:postgresql://localhost:5432/exercicio2";
        String user = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");
        
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, user, password);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao tentar conectar ao banco: " + e.getMessage());
            return false;
        }
    }

    public boolean desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            return false;
        }
    }

    public boolean inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (codigo, login, senha, sexo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, usuario.getId());
            comando.setString(2, usuario.getNomeUsuario());
            comando.setString(3, usuario.getSenha());
            comando.setString(4, usuario.getSexo());
            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET login = ?, senha = ?, sexo = ? WHERE codigo = ?";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, usuario.getNomeUsuario());
            comando.setString(2, usuario.getSenha());
            comando.setString(3, usuario.getSexo());
            comando.setInt(4, usuario.getId());
            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE codigo = ?";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);
            comando.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir usuário: " + e.getMessage());
            return false;
        }
    }

    public List<Usuario> obterTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY codigo";
        try (PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {
            while (resultado.next()) {
                Usuario u = new Usuario(
                        resultado.getInt("codigo"),
                        resultado.getString("login"),
                        resultado.getString("senha"),
                        resultado.getString("sexo")
                );
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuários: " + e.getMessage());
        }
        return usuarios;
    }
}