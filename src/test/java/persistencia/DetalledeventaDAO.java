package persistencia;

import dominio.Detalledeventa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DetalledeventaDAO {

    @Test
    public void probarDetalleVenta() {
        Detalledeventa detalle = new Detalledeventa(1, 1, 5, 3, 10.50);

        System.out.println("====== DETALLE DE VENTA CREADO EN MEMORIA ======");
        System.out.println("Cantidad: " + detalle.getCantidad());
        System.out.println("Precio Unitario: $" + detalle.getPrecioUnitario());
        System.out.println("Subtotal Calculado: $" + detalle.getSubtotal());

        assertEquals(3, detalle.getCantidad());
        assertEquals(31.5, detalle.getSubtotal(), 0.001);

        System.out.println("================================================");
    }
}