package persistencia;

import dominio.Venta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDateTime;

public class VentaDAOTest {

    @Test
    public void testFlujoVentaCompleto() {
        VentaDAO dao = new VentaDAO();
        Venta nuevaVenta = new Venta(0, LocalDateTime.now(), 150.00, 1);

        boolean registrado = dao.insertar(nuevaVenta);
        Assertions.assertTrue(registrado);

        Venta consultada = dao.buscarPorId(nuevaVenta.getIdVenta());
        Assertions.assertNotNull(consultada);
        Assertions.assertEquals(150.00, consultada.getTotal());
    }
}