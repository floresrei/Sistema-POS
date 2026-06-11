package dominio;
import java.time.LocalDateTime;

public class Venta {
    private int idVenta;
    private LocalDateTime fecha;
    private double total;
    private int idCliente;

    public Venta() {}

    public Venta(int idVenta, LocalDateTime fecha, double total, int idCliente) {
        setIdVenta(idVenta);
        setFecha(fecha);
        setTotal(total);
        setIdCliente(idCliente);
    }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) {
        if (idVenta < 0) throw new IllegalArgumentException("El ID de la venta no puede ser negativo.");
        this.idVenta = idVenta;
    }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) {
        if (fecha == null) throw new IllegalArgumentException("La fecha de la venta no puede ser nula.");
        this.fecha = fecha;
    }

    public double getTotal() { return total; }
    public void setTotal(double total) {
        if (total < 0) throw new IllegalArgumentException("El total de la venta no puede ser negativo.");
        this.total = total;
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) {
        if (idCliente <= 0) throw new IllegalArgumentException("Debe asignar un ID de cliente válido.");
        this.idCliente = idCliente;
    }
}