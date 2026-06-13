package persistencia;

import dominio.Venta;
import utilerias.ConexionDB;
import java.sql.*;
import java.time.LocalDateTime;

public class VentaDAO {

    public boolean insertar(Venta venta) {
        String sql = "INSERT INTO Ventas (fecha, total, id_cliente) VALUES (?, ?, ?)";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setTimestamp(1, Timestamp.valueOf(venta.getFecha()));
            ps.setDouble(2, venta.getTotal());
            ps.setInt(3, venta.getIdCliente());

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
            System.err.println("❌ ERROR EN SQL SERVER (Al insertar venta): " + e.getMessage());
            return false;
        }
    }

    public Venta buscarPorId(int idVenta) {
        String sql = "SELECT * FROM Ventas WHERE id_venta = ?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Venta(
                            rs.getInt("id_venta"),
                            rs.getTimestamp("fecha").toLocalDateTime(),
                            rs.getDouble("total"),
                            rs.getInt("id_cliente")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ ERROR EN SQL SERVER (Al buscar venta): " + e.getMessage());
        }
        return null;
    }

    // Ejecución directa (Botón Play verde en IntelliJ)
    public static void main(String[] args) {
        VentaDAO dao = new VentaDAO();

        System.out.println("====== EJECUTANDO VENTADAO DIRECTAMENTE ======");

        // Creamos un ID de cliente dinámico/ficticio o usa uno que sepas que ya existe en tu tabla Clientes
        int idClienteExistente = 1;

        Venta ventaPrueba = new Venta(0, LocalDateTime.now(), 299.99, idClienteExistente);

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
            System.out.println("💡 Consejo: Asegúrate de que las credenciales en ConexionDB sean correctas y que el id_cliente (" + idClienteExistente + ") exista en tu tabla de Clientes.");
        }
    }
}