package sistemapos.persistencia;

import sistemapos.dominio.Categoria;
import java.sql.*;

public class CategoriaDAO {


    private static final String URL = "workstation id=SistemaPos_BD.mssql.somee.com;packet size=4096;user id=reinaflores_SQLLogin_1;pwd=ReinaSomee;data source=SistemaPos_BD.mssql.somee.com;persist security info=False;initial catalog=SistemaPos_BD;TrustServerCertificate=True";
    private static final String USUARIO = "reinaflores_SQLLogin_1";
    private static final String CLAVE = "ReinaSomee";

    private Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

    // El método exacto que necesita tu prueba ProductoDAOTest
    public boolean insertar(Categoria categoria) {
        String sql = "INSERT INTO Categorias (nombre) VALUES (?)";
        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, categoria.getNombre());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        categoria.setIdCategoria(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("❌ ERROR AL INSERTAR CATEGORÍA: " + e.getMessage());
            return false;
        }
    }
}