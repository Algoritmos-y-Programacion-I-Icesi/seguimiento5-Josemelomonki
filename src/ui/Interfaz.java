package ui;

import model.Atraccion;
import model.Parque;

import java.util.Scanner;

/**
 * Clase de interfaz de usuario del sistema MagicWorld.
 * Contiene el metodo main y el menu de opciones.
 * Se comunica con la clase Parque (controladora) para ejecutar las operaciones.
 */
public class Interfaz {

    // Objeto controlador del parque
    private static Parque parque = new Parque("MagicWorld");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("  Bienvenido al Sistema de Gestion MagiWorld");
        System.out.println("=============================================");

        int opcion = -1;

        while (opcion != 0) {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarSimuladorVirtual();
                    break;
                case 2:
                    registrarJuegoInfantil();
                    break;
                case 3:
                    registrarEspectaculoPirotecnico();
                    break;
                case 4:
                    registrarVisitantesAtraccion();
                    break;
                case 5:
                    mostrarIngresosDiarios();
                    break;
                case 6:
                    mostrarAtraccionesClasifRiesgo();
                    break;
                case 7:
                    generarReporteOperaciones();
                    break;
                case 8:
                    generarClasificacionRiesgo();
                    break;
                case 0:
                    System.out.println("\nHasta luego. Cerrando el sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    // ---------------------------------------------------------------
    // MENU
    // ---------------------------------------------------------------

    public static void mostrarMenu() {
        System.out.println("\n----- MENU PRINCIPAL -----");
        System.out.println("1. Registrar Simulador Virtual");
        System.out.println("2. Registrar Juego Infantil");
        System.out.println("3. Registrar Espectáculo Pirotécnico");
        System.out.println("4. Registrar visitantes");
        System.out.println("5. Ver ingresos diarios");
        System.out.println("6. Ver atracciones con clasificación de riesgo");
        System.out.println("7. Generar reporte de operaciones");
        System.out.println("8. Generar reportes alertas de capacidad");
        System.out.println("0. Salir");
        System.out.println("--------------------------");
    }

    // ---------------------------------------------------------------
    // REGISTRO DE ATRACCIONES
    // ---------------------------------------------------------------

    /**
     * Solicita los datos al usuario y registra un simulador virtual.
     */
    public static void registrarSimuladorVirtual() {
        System.out.println("\n-- Registrar Simulador Virtual --");
        String nombre        = leerTexto("Nombre de la atracción: ");
        String zona          = leerTexto("Zona de ubicación: ");
        int capacidad        = leerEntero("Capacidad máxima (personas): ");
        int edadMinima       = leerEntero("Edad mínima permitida (anos): ");
        double precio        = leerDecimal("Precio de entrada ($): ");
        int estaciones       = leerEntero("Número de estaciones");
        boolean anteojos     = leerBooleano("Requiere anteojos? (s/n):");

        parque.agregarAtraccion(nombre, zona, capacidad, edadMinima, edadMinima, precio, estaciones, anteojos);
    }

    /**
     * Solicita los datos al usuario y registra un Juego infantil.
     */
    public static void registrarJuegoInfantil() {
        System.out.println("\n-- Registrar Juego Infantil --");
        String nombre        = leerTexto("Nombre de la atracción: ");
        String zona          = leerTexto("Zona de ubicación: ");
        int capacidad        = leerEntero("Capacidad máxima (personas): ");
        int edadMinima       = leerEntero("Edad mínima permitida (años): ");
        double precio        = leerDecimal("Precio de entrada ($): ");
        int edadMaxima       = leerEntero("Edad máxima permitida (años): ");
        boolean supervision  = leerBooleano("Requiere supervisión personal? (s/n):");

        parque.agregarAtraccion(nombre, zona, capacidad, edadMinima, 0, precio, edadMaxima, supervision);
    }

    /**
     * Solicita los datos al usuario y registra una Espectácuilo pirotécnico.
     */
    public static void registrarEspectaculoPirotecnico() {
        System.out.println("\n-- Registrar Espectácuilo Pirotécnico --");
        String nombre        = leerTexto("Nombre de la atraccion: ");
        String zona          = leerTexto("Zona de ubicación: ");
        int capacidad        = leerEntero("Capacidad máxima (personas): ");
        int edadMinima       = leerEntero("Edad mínima permitida (anos): ");
        double precio        = leerDecimal("Precio de entrada ($): ");
        int duracion         = leerEntero("Duración en minutos: ");
        boolean materialPeligroso    = leerBooleano("usa material peligroso? (s/n): ");


        parque.agregarAtraccion(nombre, zona, capacidad, edadMinima, precio, duracion, materialPeligroso);
    }

    /**
     * Solicita el nombre de una atracción y registra la cantidad
     * de visitantes recibidos durante el día.
     */
    public static void registrarVisitantesAtraccion() {
        System.out.println("\n-- Registrar visitantes por día --");

        String nombreAtraccion = leerTexto("Nombre de la atracción: ");
        int visitantes = leerEntero("Cantidad de visitantes del día: ");
        if (visitantes < 0) {
            System.out.println("La cantidad de visitantes no puede ser negativa.");

        parque.registrarVisitantes(nombreAtraccion, visitantes);
        }
    }

    public static void mostrarIngresosDiarios(){
        parque.mostrarIngresosDiarios();
    }

    public static void mostrarAtraccionesClasifRiesgo(){
        parque.mostrarAtraccionesClasifRiesgo();
    }

    public static void generarReporteOperaciones(){
        parque.generarReporteOperaciones();
    }

    public static void generarReporteAlertasCapacidad(){
        parque.generarReporteAlertasCapacidad();
    }


    // ---------------------------------------------------------------
    // METODOS DE LECTURA (utilitarios de consola)
    // ---------------------------------------------------------------

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public static int leerEntero(String mensaje) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(mensaje);
                valor = Integer.parseInt(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número entero válido.");
            }
        }
        return valor;
    }

    public static double leerDecimal(String mensaje) {
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(mensaje);
                valor = Double.parseDouble(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un numero valido (use punto para decimales).");
            }
        }
        return valor;
    }

    public static boolean leerBooleano(String mensaje) {
        System.out.print(mensaje);
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí");
    }
}

