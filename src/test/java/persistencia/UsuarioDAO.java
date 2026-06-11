package persistencia;

import dominio.Usuario;
import utilerias.ConexionDB;
import java.sql.*;

public class UsuarioDAO {

    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (username, password, rol) VALUES (?, ?, ?)";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public Usuario autenticar(String username, String password) {
        String sql = "SELECT * FROM Usuarios WHERE username = ? AND password = ?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("rol")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}