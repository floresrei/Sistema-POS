package sistemapos.persistencia;

import sistemapos.dominio.Detalledeventa;

public class DetalledeventaDAO {

    public static void main(String[] args) {
        System.out.println("====== EJECUTANDO PRUEBA DE DETALLE DE VENTA ======");

        // Creamos el objeto en memoria
        // Parámetros: idDetalle, idVenta, idProducto, cantidad, precioUnitario
        Detalledeventa detalle = new Detalledeventa(1, 1, 5, 3, 10.50);

        System.out.println("Cantidad registrada: " + detalle.getCantidad());
        System.out.println("Precio Unitario: $" + detalle.getPrecioUnitario());
        System.out.println("Subtotal Calculado en clase: $" + detalle.getSubtotal());

        // Validamos manualmente en lugar de usar assertEquals
        double subtotalEsperado = 31.5;
        if (detalle.getSubtotal() == subtotalEsperado) {
            System.out.println("✅ ¡ÉXITO! El subtotal se calculó correctamente ($" + detalle.getSubtotal() + ")");
        } else {
            System.out.println("❌ ¡ERROR! El subtotal esperado era $" + subtotalEsperado + " pero dio $" + detalle.getSubtotal());
        }

        System.out.println("===================================================");
    }
}