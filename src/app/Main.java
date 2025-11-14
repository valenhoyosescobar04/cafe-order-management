package app;

import java.util.Scanner;
import patrones.estructurales.FacadeSistemaCafe;

public class Main {

    private static final String[] BEBIDAS = {
            "Café Americano", "Capuchino", "Latte", "Mocca"
    };

    private static final double[] PRECIOS_BEBIDAS = {
            5000, 6500, 7000, 7500
    };

    private static final String[] COMIDAS = {
            "Sandwich", "Croissant", "Panini"
    };

    private static final double[] PRECIOS_COMIDAS = {
            8500, 7000, 9000
    };

    private static final String[] POSTRES = {
            "Brownie", "Cheesecake", "Galleta"
    };

    private static final double[] PRECIOS_POSTRES = {
            5500, 6500, 3000
    };

    public static void main(String[] args) {

        FacadeSistemaCafe facade = new FacadeSistemaCafe();
        Scanner sc = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("\n==============================================");
            System.out.println("            ☕ SISTEMA CAFETERÍA");
            System.out.println("==============================================");
            System.out.println("1. Iniciar orden");
            System.out.println("2. Agregar producto");
            System.out.println("3. Agregar crema extra");
            System.out.println("4. Agregar jarabe extra");
            System.out.println("5. Agregar observaciones");
            System.out.println("6. Finalizar y procesar orden");
            System.out.println("7. Avanzar estado de orden");
            System.out.println("8. Ver órdenes activas");
            System.out.println("9. Ver historial");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {

                case 1 -> {
                    System.out.print("Número de mesa: ");
                    int mesa = sc.nextInt();
                    facade.iniciarOrden(mesa);
                    System.out.println("✔ Orden iniciada para mesa " + mesa);
                }

                case 2 -> {
                    System.out.print("Mesa: ");
                    int mesa = sc.nextInt();

                    int categoria = seleccionarCategoria(sc);

                    if (categoria == 1) seleccionarProducto(sc, facade, mesa, "bebida", BEBIDAS, PRECIOS_BEBIDAS);
                    if (categoria == 2) seleccionarProducto(sc, facade, mesa, "comida", COMIDAS, PRECIOS_COMIDAS);
                    if (categoria == 3) seleccionarProducto(sc, facade, mesa, "postre", POSTRES, PRECIOS_POSTRES);
                }

                case 3 -> {
                    System.out.print("Mesa: ");
                    int mesa = sc.nextInt();
                    facade.agregarCremaExtra(mesa);
                    System.out.println("✔ Crema extra agregada");
                }

                case 4 -> {
                    System.out.print("Mesa: ");
                    int mesa = sc.nextInt();
                    int sabor = seleccionarSaborJarabe(sc);
                    String saborTxt = (sabor == 1) ? "Vainilla" :
                            (sabor == 2) ? "Caramelo" :
                                    "Avellana";

                    facade.agregarJarabeExtra(mesa, saborTxt);
                    System.out.println("✔ Jarabe extra agregado");
                }

                case 5 -> {
                    System.out.print("Mesa: ");
                    int mesa = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Observación: ");
                    String obs = sc.nextLine();
                    facade.agregarObservaciones(mesa, obs);
                }

                case 6 -> {
                    System.out.print("Mesa: ");
                    int mesa = sc.nextInt();
                    facade.procesarOrden(mesa);
                }

                case 7 -> {
                    System.out.print("ID de la orden: ");
                    int id = sc.nextInt();
                    facade.avanzarOrden(id);
                }

                case 8 -> facade.mostrarOrdenesActivas();

                case 9 -> facade.MementoHistorial();

                case 0 -> System.out.println("👋 Saliendo...");

                default -> System.out.println("❌ Opción inválida");
            }

        } while (opcion != 0);

        sc.close();
    }

    // =============================================================
    // MÉTODOS DE SELECCIÓN NUMÉRICA
    // =============================================================

    private static int seleccionarCategoria(Scanner sc) {
        System.out.println("\nSeleccione categoría:");
        System.out.println("1. Bebidas");
        System.out.println("2. Comidas");
        System.out.println("3. Postres");
        System.out.print("Opción: ");
        return sc.nextInt();
    }

    private static void seleccionarProducto(
            Scanner sc,
            FacadeSistemaCafe facade,
            int mesa,
            String tipo,
            String[] nombres,
            double[] precios) {

        System.out.println("\nSeleccione producto:");

        for (int i = 0; i < nombres.length; i++) {
            System.out.println((i + 1) + ". " + nombres[i] + " - $" + precios[i]);
        }

        System.out.print("Opción: ");
        int op = sc.nextInt();

        int i = op - 1;
        facade.agregarProducto(mesa, tipo, nombres[i], precios[i]);
        System.out.println("✔ Producto agregado");
    }

    private static int seleccionarSaborJarabe(Scanner sc) {
        System.out.println("\nSeleccione sabor de jarabe:");
        System.out.println("1. Vainilla");
        System.out.println("2. Caramelo");
        System.out.println("3. Avellana");
        System.out.print("Opción: ");
        return sc.nextInt();
    }
}
