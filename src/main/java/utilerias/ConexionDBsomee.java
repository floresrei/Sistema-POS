package utilerias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDBsomee {

    // 1. Datos extraídos exactamente de tu cadena de Somee (Perfil: Abigail)
    private static final String URL = "workstation id=SistemaposDB.mssql.somee.com;packet size=4096;user id=Abigail23_SQLLogin_1;pwd=Abigail23Londra;data source=SistemaposDB.mssql.somee.com;persist security info=False;initial catalog=SistemaposDB;TrustServerCertificate=True";
    private static final String USER = "Abigail23_SQLLogin_1";
    private static final String PASSWORD = "Abigail23Londra";

    // 2. Formato de URL oficial requerido por Microsoft JDBC Driver para Java

    public static Connection obtenerConexion() throws SQLException {
        try {
            // Carga el Driver oficial de SQL Server en la memoria de Java
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: No se encontró el Driver de SQL Server en tu proyecto.");
            throw new SQLException("Driver JDBC no encontrado", e);
        } catch (SQLException e) {
            System.err.println("❌ Error de Conexión: Revisa el estado de Somee o tus credenciales.");
            throw e;
        }
    }
}