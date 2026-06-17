package sistemapos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // 1. Datos del servidor remoto de Somee
    private static final String SERVIDOR = "SistemaPos_BD.mssql.somee.com";
    private static final String BASE_DATOS = "SistemaPos_BD";
    private static final String USUARIO = "reinaflores_SQLLogin_1";
    private static final String PASSWORD = "ReinaSomee";

    // 2. Cadena de conexión limpia y bien estructurada
    private static final String URL = "jdbc:sqlserver:(localdb)\\MSSQLLocalDB//SistemaPos_BD.backup.somee.com/SistemaPos_BD_MSSql_Database_Backup" + SERVIDOR + ";"
            + "databaseName=" + BASE_DATOS + ";"
            + "user=" + USUARIO + ";"
            + "password=" + PASSWORD + ";"
            + "encrypt=false;"
            + "trustServerCertificate=true;";

    /**
     * Método para obtener la conexión a Somee
     */
    public static Connection getConexion() throws SQLException {
        try {
            // Cargar el Driver oficial en memoria
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Retornar la conexión usando la URL armada
            return DriverManager.getConnection(URL);

        } catch (ClassNotFoundException e) {
            throw new SQLException("Error: No se encontró el Driver de SQL Server (mssql-jdbc) en el proyecto.", e);
        }
    }
}