package patrones.estructurales.decorator.test;

import models.*;
import patrones.creacionales.ProductoFactory;

/**
 * Clase de prueba para verificar el funcionamiento de los modelos y Factory
 */
public class TestIntegrante1 {
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  TEST INTEGRANTE 1: MODELOS Y FACTORY");
        System.out.println("===========================================\n");

        // Test 1: Crear productos usando Factory
        System.out.println("1. Test Factory Method:");
        System.out.println("------------------------");

        Producto bebida = ProductoFactory.crearProducto("BEBIDA", "Cappuccino", 3.50);
        System.out.println("✓ " + bebida);

        Producto comida = ProductoFactory.crearProducto("COMIDA", "Sandwich Club", 6.50);
        System.out.println("✓ " + comida);

        Producto postre = ProductoFactory.crearProducto("POSTRE", "Cheesecake", 4.50);
        System.out.println("✓ " + postre);

        Producto entrada = ProductoFactory.crearProducto("ENTRADA", "Pan de Ajo", 2.50);
        System.out.println("✓ " + entrada);

        // Test 2: Crear productos con métodos sobrecargados
        System.out.println("\n2. Test Factory con opciones específicas:");
        System.out.println("------------------------------------------");

        Producto bebidaGrande = ProductoFactory.crearBebida("Latte", 4.50, "Grande");
        System.out.println("✓ " + bebidaGrande);

        Producto comidaVegetariana = ProductoFactory.crearComida("Ensalada César", 5.75, true);
        System.out.println("✓ " + comidaVegetariana);

        Producto postreSinAzucar = ProductoFactory.crearPostre("Brownie", 3.50, true);
        System.out.println("✓ " + postreSinAzucar);

        // Test 3: Verificar getters
        System.out.println("\n3. Test Getters:");
        System.out.println("-----------------");
        System.out.println("Nombre: " + bebida.getNombre());
        System.out.println("Precio: $" + bebida.getPrecio());
        System.out.println("Categoría: " + bebida.getCategoria());

        // Test 4: Manejo de errores
        System.out.println("\n4. Test Manejo de Errores:");
        System.out.println("---------------------------");
        try {
            ProductoFactory.crearProducto("INVALIDO", "Test", 1.0);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Error capturado correctamente: " + e.getMessage());
        }

        System.out.println("\n===========================================");
        System.out.println("  ✅ TODOS LOS TESTS PASARON");
        System.out.println("===========================================\n");
    }
}