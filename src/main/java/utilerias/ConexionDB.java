package utilerias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "workstation id=SistemaPosDB.mssql.somee.com;packet size=4096;user id=reinaflores_SQLLogin_3;pwd=Reina19SALOMON;data source=SistemaPosDB.mssql.somee.com;persist security info=False;initial catalog=SistemaPosDB;TrustServerCertificate=True";
    private static final String USER = "reinaflores_SQLLogin_3";
    private static final String PASSWORD = "Reina19SALOMON";

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC no encontrado", e);
        }
    }
}
