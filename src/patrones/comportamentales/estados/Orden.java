package patrones.comportamentales.estados;

import models.Producto;
import patrones.comportamentales.estados.StateOrden;
import patrones.comportamentales.estados.EstadoPendiente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Orden {
    private static int contadorId = 1;
    private int id;
    private int numeroMesa;
    private List<Producto> productos;
    private StateOrden estado;
    private LocalDateTime fechaCreacion;
    private String observaciones;

    public Orden(int numeroMesa) {
        this.id = contadorId++;
        this.numeroMesa = numeroMesa;
        this.productos = new ArrayList<>();
        this.estado = new EstadoPendiente();
        this.fechaCreacion = LocalDateTime.now();
        this.observaciones = "";
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void setObservaciones(String obs) {
        this.observaciones = obs;
    }

    public double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public void avanzarEstado() {
        estado.siguiente(this);
    }

    public void setEstado(StateOrden nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public int getId() { return id; }
    public int getNumeroMesa() { return numeroMesa; }
    public List<Producto> getProductos() { return new ArrayList<>(productos); }
    public String getEstado() { return estado.getEstado(); }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n┌─────────────────────────────────────────┐\n"));
        sb.append(String.format("│ ORDEN #%03d - Mesa %d                  │\n", id, numeroMesa));
        sb.append(String.format("│ Estado: %-30s │\n", getEstado()));
        sb.append(String.format("│ Fecha: %-31s │\n", fechaCreacion.format(formatter)));
        sb.append(String.format("├─────────────────────────────────────────┤\n"));
        for (Producto p : productos) {
            sb.append(String.format("│ • %-37s │\n", p.toString()));
        }
        if (!observaciones.isEmpty()) {
            sb.append(String.format("│ Obs: %-34s │\n", observaciones));
        }
        sb.append(String.format("├─────────────────────────────────────────┤\n"));
        sb.append(String.format("│ TOTAL: $%-31.2f │\n", calcularTotal()));
        sb.append(String.format("└─────────────────────────────────────────┘\n"));
        return sb.toString();
    }
}
