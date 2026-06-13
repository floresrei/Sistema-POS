package sistemapos.persistencia;

import sistemapos.dominio.Usuario;
import sistemapos.util.ConexionBD;
import java.sql.*;

public class UsuarioDAO {

    public boolean insertar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nombre_usuario, contrasenia, rol) VALUES (?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasenia());
            ps.setString(3, usuario.getRol());

            int filas = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
            return filas > 0;
        }
    }

    // Método principal del Login: Valida si las credenciales son correctas
    public Usuario autenticar(String nombreUsuario, String contrasenia) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ? AND contrasenia = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasenia);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Si encuentra coincidencia, devuelve el objeto Usuario con sus privilegios/rol
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre_usuario"),
                            rs.getString("contrasenia"),
                            rs.getString("rol")
                    );
                }
            }
        }
        // Si no coincide el usuario o la contraseña, retorna null (Credenciales inválidas)
        return null;
    }
}
