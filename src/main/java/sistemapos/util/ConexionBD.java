package sistemapos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String SERVIDOR = "SistemaPos_BD.mssql.somee.com"; // Ej: "sistemapos.mssql.somee.com"
    private static final String PUERTO = "MPID5029842";                  // Puerto por defecto de SQL Server
    private static final String BASE_DATOS = "SistemaPos_BD";   // Nombre de la BD que creaste en Somee
    private static final String USUARIO = "reinaflores_SQLLogin_1";         // El usuario de la BD (no el de login a la web)
    private static final String PASSWORD = "ReinaSomee";       // La contraseña de ese usuario

    // Cadena de conexión optimizada para el SQL Server remoto de Somee
    private static final String URL = "workstation id=SistemaPos_BD.mssql.somee.com;packet size=4096;user id=reinaflores_SQLLogin_1;pwd=ReinaSomee;data source=SistemaPos_BD.mssql.somee.com;persist security info=False;initial catalog=SistemaPos_BD;TrustServerCertificate=True" + SERVIDOR + "SistemaPos_BD.mssql.somee.com" + PUERTO + "MPID5029842"
            + "databaseName=" + BASE_DATOS + ";"
            + "encrypt=true;" // Somee suele requerir conexiones seguras en versiones recientes
            + "trustServerCertificate=true;"; // Evita problemas de certificados SSL no válidos en desarrollo

    /**
     * Método público y estático para obtener la conexión a la base de datos.
     * @return Connection objeto de conexión activo
     * @throws SQLException si ocurre un error al conectar
     */
    public static Connection getConexion() throws SQLException {
        try {
            // Forzar la carga del Driver oficial de Microsoft SQL Server en memoria
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establecer y retornar la conexión
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);

        } catch (ClassNotFoundException e) {
            // Error si falta la dependencia de Maven/Gradle o el archivo .jar
            throw new SQLException("Error Crítico: No se encontró el Driver de SQL Server (mssql-jdbc) en el proyecto.", e);
        }
    }
}