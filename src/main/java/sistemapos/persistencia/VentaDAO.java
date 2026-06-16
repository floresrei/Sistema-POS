package sistemapos.persistencia;

import sistemapos.dominio.Venta;
import java.sql.*;
import java.time.LocalDateTime;

public class VentaDAO {

    private static final String URL = "workstation id=SistemaPos_BD.mssql.somee.com;packet size=4096;user id=reinaflores_SQLLogin_1;pwd=ReinaSomee;data source=SistemaPos_BD.mssql.somee.com;persist security info=False;initial catalog=SistemaPos_BD;TrustServerCertificate=True";
    private static final String USUARIO = "reinaflores_SQLLogin_1";
    private static final String CLAVE = "ReinaSomee";


    private Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

    public boolean insertar(Venta venta) {
        String sql = "INSERT INTO Ventas (fecha, total, id_cliente) VALUES (?, ?, ?)";
        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setTimestamp(1, Timestamp.valueOf(venta.getFecha()));
            ps.setDouble(2, venta.getTotal());

            if (venta.getIdCliente() <= 0) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, venta.getIdCliente());
            }

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        venta.setIdVenta(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("❌ ERROR EN Somee (Al insertar venta): " + e.getMessage());
            return false;
        }
    }

    public Venta buscarPorId(int idVenta) {
        String sql = "SELECT * FROM Ventas WHERE id_venta = ?";

        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Timestamp timestamp = rs.getTimestamp("fecha");
                    LocalDateTime fecha = (timestamp != null) ? timestamp.toLocalDateTime() : LocalDateTime.now();

                    return new Venta(
                            rs.getInt("id_venta"),
                            fecha,
                            rs.getDouble("total"),
                            rs.getInt("id_cliente")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ ERROR EN Somee (Al buscar venta): " + e.getMessage());
        }
        return null;
    }

    // Ejecución directa para pruebas
    public static void main(String[] args) {
        VentaDAO dao = new VentaDAO();

        System.out.println("====== EJECUTANDO VENTADAO DIRECTAMENTE ======");

        int idClientePrueba = 1;
        Venta ventaPrueba = new Venta(0, LocalDateTime.now(), 299.99, idClientePrueba);

        System.out.println("Intentando insertar registro en la base de datos...");
        boolean inserto = dao.insertar(ventaPrueba);

        if (inserto) {
            System.out.println("✅ ¡Éxito al insertar! ID Autogenerado asignado: " + ventaPrueba.getIdVenta());
            Venta encontrada = dao.buscarPorId(ventaPrueba.getIdVenta());
            if (encontrada != null) {
                System.out.println("✅ ¡Éxito al buscar! Total recuperado de BD: $" + encontrada.getTotal());
            }
        } else {
            System.out.println("❌ No se pudo completar la operación.");
            System.out.println("💡 Consejos de solución:");
            System.out.println("1. Verifica que las variables URL, USUARIO y CLAVE arriba en el código sean las correctas.");
            System.out.println("2. Asegúrate de tener el driver JDBC de Somee (archivo .jar) agregado a las librerías de tu proyecto.");
            System.out.println("3. Si usaste el id_cliente " + idClientePrueba + ", verifica que exista en la tabla Clientes.");
        }
    }
}