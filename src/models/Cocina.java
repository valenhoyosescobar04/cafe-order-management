package models;

import patrones.comportamentales.Observador;

public class Cocina implements Observador {
    private String nombre;

    public Cocina(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(Orden orden, String evento) {
        if (evento.equals("NUEVA_ORDEN")) {
            System.out.println("\n🔔 [COCINA " + nombre + "] Nueva orden recibida #" + orden.getId());
            System.out.println("   Mesa: " + orden.getNumeroMesa());
            System.out.println("   Productos: " + orden.getProductos().size());
        }
    }
}