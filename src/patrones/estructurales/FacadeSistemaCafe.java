package patrones.estructurales;

import models.Orden;
import models.Cocina;
import models.Producto;
import models.SistemaNotificaciones;
import models.Mesero;

import patrones.comportamentales.MementoHistorial;
import patrones.creacionales.MenuSingleton;
import patrones.creacionales.OrdenBuilder;
import patrones.creacionales.ProductoFactory;

import adicionales.ExtraCrema;
import adicionales.ExtraJarabe;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class FacadeSistemaCafe {

    private MenuSingleton menu;
    private SistemaNotificaciones notificaciones;
    private MementoHistorial historial;

    // Órdenes finalizadas
    private List<Orden> ordenesActivas;

    // Órdenes en construcción (BUILDER)
    private HashMap<Integer, OrdenBuilder> ordenesTemporales;

    public FacadeSistemaCafe() {
        this.menu = MenuSingleton.getInstancia();
        this.notificaciones = new SistemaNotificaciones();
        this.historial = MementoHistorial.getInstancia();

        this.ordenesActivas = new ArrayList<>();
        this.ordenesTemporales = new HashMap<>();

        // Registrar observadores (OBSERVER)
        notificaciones.agregarObservador(new Cocina("Principal"));
        notificaciones.agregarObservador(new Mesero("Juan"));
        notificaciones.agregarObservador(new Mesero("María"));
    }

    /* ============================================================
       MENÚ (SINGLETON)
    ============================================================ */
    public void mostrarMenu() {
        menu.mostrarMenu();
    }

    /* ============================================================
       INICIAR ORDEN (BUILDER)
    ============================================================ */
    public void iniciarOrden(int mesa) {
        ordenesTemporales.put(mesa, new OrdenBuilder(mesa));
    }

    /* ============================================================
       AGREGAR PRODUCTO (FACTORY)
    ============================================================ */
    public void agregarProducto(int mesa, String tipo, String nombre, double precio) {

        Producto producto = ProductoFactory.crearProducto(tipo, nombre, precio);

        ordenesTemporales.get(mesa).agregarProducto(producto);
    }

    /* ============================================================
       DECORATOR — extras sobre el último producto agregado
    ============================================================ */
    private Producto obtenerUltimoProducto(int mesa) {
        Orden orden = ordenesTemporales.get(mesa).construir();
        List<Producto> productos = orden.getProductos();

        if (productos.isEmpty()) return null;

        return productos.get(productos.size() - 1);
    }

    public void agregarCremaExtra(int mesa) {
        Producto ultimo = obtenerUltimoProducto(mesa);
        if (ultimo == null) return;

        Orden orden = ordenesTemporales.get(mesa).construir();
        List<Producto> productos = orden.getProductos();

        productos.set(productos.size() - 1, new ExtraCrema(ultimo));
    }

    public void agregarJarabeExtra(int mesa, String sabor) {
        Producto ultimo = obtenerUltimoProducto(mesa);
        if (ultimo == null) return;

        Orden orden = ordenesTemporales.get(mesa).construir();
        List<Producto> productos = orden.getProductos();

        productos.set(productos.size() - 1, new ExtraJarabe(ultimo, sabor));
    }

    /* ============================================================
       OBSERVACIONES
    ============================================================ */
    public void agregarObservaciones(int mesa, String obs) {
        ordenesTemporales.get(mesa).conObservaciones(obs);
    }

    /* ============================================================
       FINALIZAR ORDEN (BUILDER + OBSERVER)
    ============================================================ */
    public void procesarOrden(int mesa) {
        OrdenBuilder builder = ordenesTemporales.get(mesa);

        Orden orden = builder.construir();
        ordenesActivas.add(orden);

        System.out.println("\n🎯 Procesando orden #" + orden.getId());
        notificaciones.notificar(orden, "NUEVA_ORDEN");

        System.out.println(orden);

        ordenesTemporales.remove(mesa);
    }

    /* ============================================================
       AVANZAR ESTADO (STATE + MEMENTO)
    ============================================================ */
    public void avanzarOrden(int idOrden) {
        Orden orden = buscarOrdenActiva(idOrden);

        if (orden != null) {

            orden.avanzarEstado(); // STATE

            if (orden.getEstado().equals("LISTA")) {
                notificaciones.notificar(orden, "ORDEN_LISTA");
            }

            if (orden.getEstado().equals("ENTREGADA")) {
                ordenesActivas.remove(orden);
                historial.guardarOrden(orden); // MEMENTO
                System.out.println("💾 Orden guardada en historial");
            }

        } else {
            System.out.println("❌ Orden no encontrada");
        }
    }

    private Orden buscarOrdenActiva(int id) {
        return ordenesActivas.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /* ============================================================
       CONSULTAS
    ============================================================ */
    public void mostrarOrdenesActivas() {
        System.out.println("\n--- ÓRDENES ACTIVAS ---");

        if (ordenesActivas.isEmpty()) {
            System.out.println("  No hay órdenes activas.\n");
            return;
        }

        ordenesActivas.forEach(System.out::println);
    }

    public void MementoHistorial() {
        historial.mostrarHistorial();
    }
}
