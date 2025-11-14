package patrones.creacionales;

import models.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuSingleton {
    private static MenuSingleton instancia;
    private Map<String, List<Producto>> categorias;

    private MenuSingleton() {
        categorias = new HashMap<>();
        inicializarMenu();
    }

    public static MenuSingleton getInstancia() {
        if (instancia == null) {
            instancia = new MenuSingleton();
        }
        return instancia;
    }

    private void inicializarMenu() {
        // Bebidas
        List<Producto> bebidas = new ArrayList<>();
        bebidas.add(ProductoFactory.crearProducto("BEBIDA", "Café Americano", 2.50));
        bebidas.add(ProductoFactory.crearProducto("BEBIDA", "Cappuccino", 3.50));
        bebidas.add(ProductoFactory.crearProducto("BEBIDA", "Latte", 3.75));
        bebidas.add(ProductoFactory.crearProducto("BEBIDA", "Té Verde", 2.00));
        categorias.put("BEBIDA", bebidas);

        // Comidas
        List<Producto> comidas = new ArrayList<>();
        comidas.add(ProductoFactory.crearProducto("COMIDA", "Sandwich Club", 6.50));
        comidas.add(ProductoFactory.crearProducto("COMIDA", "Ensalada César", 5.75));
        comidas.add(ProductoFactory.crearProducto("COMIDA", "Pasta Alfredo", 8.50));
        categorias.put("COMIDA", comidas);

        // Postres
        List<Producto> postres = new ArrayList<>();
        postres.add(ProductoFactory.crearProducto("POSTRE", "Cheesecake", 4.50));
        postres.add(ProductoFactory.crearProducto("POSTRE", "Brownie", 3.50));
        postres.add(ProductoFactory.crearProducto("POSTRE", "Tiramisú", 5.00));
        categorias.put("POSTRE", postres);

        // Entradas
        List<Producto> entradas = new ArrayList<>();
        entradas.add(ProductoFactory.crearProducto("ENTRADA", "Pan de Ajo", 2.50));
        entradas.add(ProductoFactory.crearProducto("ENTRADA", "Alitas Picantes", 5.50));
        categorias.put("ENTRADA", entradas);
    }

    public List<Producto> obtenerCategoria(String categoria) {
        return new ArrayList<>(categorias.getOrDefault(categoria.toUpperCase(), new ArrayList<>()));
    }

    public void mostrarMenu() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║         MENÚ CAFÉ DEL BOSQUE              ║");
        System.out.println("╚═══════════════════════════════════════════╝\n");

        for (Map.Entry<String, List<Producto>> entry : categorias.entrySet()) {
            System.out.println("▸ " + entry.getKey() + ":");
            int i = 1;
            for (Producto p : entry.getValue()) {
                System.out.println("  " + i++ + ". " + p);
            }
            System.out.println();
        }
    }
}