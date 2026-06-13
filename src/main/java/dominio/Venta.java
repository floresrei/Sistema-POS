package dominio;

import java.time.LocalDateTime;

public class Venta {
    private int idVenta;
    private LocalDateTime fecha;
    private double total;
    private int idCliente;

    // Constructor vacío requerido para mapeos genéricos
    public Venta() {}

    // Constructor lleno para instanciar rápidamente
    public Venta(int idVenta, LocalDateTime fecha, double total, int idCliente) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.total = total;
        this.idCliente = idCliente;
    }

    // Getters y Setters
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
}