package model;

import java.util.ArrayList;

/**
 * Clase controladora del sistema MagicWorld.
 * Administra la lista de atracciones y centraliza las operaciones
 * que la interfaz de usuario necesita realizar.
 */
public class Parque {

    private String nombre;
    private ArrayList<Atraccion> atracciones;

    /**
     * Constructor del Parque. Inicializa el ArrayList de atracciones.
     */
    public Parque(String nombre) {
        this.nombre = nombre;
        this.atracciones = new ArrayList<>();
    }

    /**
     * Retorna la lista completa de atracciones registradas.
     */
    public ArrayList<Atraccion> getAtracciones() {
        return atracciones;
    }

    /*
     * Los visitantes se inicializan en 0 y luego pueden registrarse
     * mediante el metodo registrarVisitantes.
     */
    public void agregarAtraccion(String nombre, String zonaUbicacion, int capacidadMaxima,
            int edadMinimaAnios, double precioEntrada) {

        Atraccion simulador = new Simulador(
                0, // numeroEstaciones
                false, // requiereAnteojos
                nombre,
                zonaUbicacion,
                capacidadMaxima,
                edadMinimaAnios,
                0,
                precioEntrada);
        atracciones.add(simulador);

        Atraccion espectaculo = new Espectaculo(
                "Tipo de espectaculo",
                0, // duracionMinutos
                nombre,
                zonaUbicacion,
                capacidadMaxima,
                edadMinimaAnios,
                0,
                precioEntrada);
        atracciones.add(espectaculo);

        Atraccion juegoInfantil = new JuegoInfantil(
                0, // edadMaxima
                false, // supervision
                nombre,
                zonaUbicacion,
                capacidadMaxima,
                edadMinimaAnios,
                0,
                precioEntrada);
        atracciones.add(juegoInfantil);
    }

    /**
     * Busca una atraccion por nombre y registra sus visitantes del dia.
     * 
     * @param nombreAtraccion  nombre de la atraccion
     * @param visitantesPorDia cantidad de visitantes del dia
     */
    public void registrarVisitantes(String nombreAtraccion, int visitantesPorDia) {
        Atraccion atraccionEncontrada = buscarAtraccionPorNombre(nombreAtraccion);

        if (atraccionEncontrada == null) {
            System.out.println("No se encontro una atraccion con el nombre: " + nombreAtraccion);
        } else {
            atraccionEncontrada.setVisitantesPorDia(visitantesPorDia);
        }
    }

    /**
     * Busca una atraccion por su nombre.
     * 
     * @param nombreAtraccion nombre de la atraccion buscada
     * @return la atraccion encontrada o null si no existe
     */
    public Atraccion buscarAtraccionPorNombre(String nombreAtraccion) {
        for (Atraccion atraccion : atracciones) {
            if (atraccion.getNombre().equalsIgnoreCase(nombreAtraccion)) {
                return atraccion;
            }
        }

        return null;
    }

    // ---------------------------------------------------------------
    // CALCULOS Y REPORTES
    // ---------------------------------------------------------------

    /**
     * Descripcion: Calcula el ingreso total diario del parque sumando los ingresos de cada atraccion. 
     * Entradas:
     * @param atracciones lista de atracciones del parque
     * Salidas:
     * @return el ingreso total diario del parque
     * Ejemplo:
     * Si el parque tiene 3 atracciones con ingresos diarios de $1000, $2000 y $1500 respectivamente,
     */
    public double calcularIngresoTotalDiario() {
        double total = 0;
        for (Atraccion atraccion : atracciones) {
            total += atraccion.calcularIngresoDiario();
        }
        return total;
    }

    /**
     * Descripcion: Muestra el ingreso diario de cada atraccion del parque.
     * Entradas:
     * @param atracciones lista de atracciones del parque
     * Salidas:
     * @return void, imprime en consola el nombre de cada atraccion junto con su ingreso diario
     * Ejemplo:
     * Si el parque tiene 3 atracciones con ingresos diarios de $1000, $2000 y $1500 respectivamente, se imprimira:
     * Atraccion A: $1000.00
     * Atraccion B: $2000.00
     * Atraccion C: $1500.00
     */
    public void mostrarIngresosDiarios() {
        for (Atraccion atraccion : atracciones) {
            System.out.println(atraccion.getNombre() + ": $" + String.format("%,.2f", atraccion.calcularIngresoDiario()));
        }
    }

    /**
     * Descripcion: Genera un reporte detallado de cada atraccion del parque, incluyendo su informacion basica, ingreso diario y si requiere mantenimiento.
     * Entradas:
     * @param atracciones lista de atracciones del parque
     * Salidas:
     * @return void, imprime en consola un reporte detallado de cada atraccion
     * Ejemplo:
     * Si Simulador con ingreso diario de $5000.00 y requiere mantenimiento, se imprimira:
     * --------------------------------------------
     * Nombre        : Simulador
     * Zona          : Zona A
     * Capacidad max : 50 personas
     * Edad minima   : 12 años
     * Visitantes hoy: 60
     * Precio entrada: $100.00
     * Ingreso diario: $5000.00
     * Requiere mantenimiento: True
     */
    public void generarReporteOperaciones() {
        for (Atraccion atraccion : atracciones) {
            System.out.println(atraccion.toString() + "\nRequiere mantenimiento: " + atraccion.requiereMantenimiento());
        }
    }

    /**
     * Descripcion: Muestra el nivel de riesgo de cada atraccion que implementa la interfaz Clasificable.
     * Entradas:
     * @param atracciones lista de atracciones del parque
     * Salidas:
     * @return void, imprime en consola el nombre de cada atraccion clasificable junto con su nivel de riesgo
     * Ejemplo:
     * Si el parque tiene un Simulador con nivel de riesgo "Alto" y un Juego Infantil con nivel de riesgo "Bajo", se imprimira:
     * Simulador: Nivel de riesgo - Alto
     * Juego Infantil: Nivel de riesgo - Bajo
     */
    public void mostrarAtraccionesClasifRiesgo() {
        for (Atraccion atraccion : atracciones) {
            if (atraccion instanceof Clasificable) {
                Clasificable clasif = (Clasificable) atraccion;
                System.out.println(atraccion.getNombre() + ": Nivel de riesgo - " + clasif.getNivelRiesgo());
            }
        }
    }

    /**
     * Descripcion: Genera un reporte de alertas cuando una atraccion supera su capacidad maxima.
     * Entradas:
     * @param atracciones lista de atracciones del parque
     * Salidas:
     * @return void, imprime en consola las alertas de capacidad excedida
     * Ejemplo:
     * Si Simulador tiene una capacidad maxima de 50 personas pero se registran 60 visitantes, se imprimira:
     * Simulador ha superado su capacidad maxima. Visitantes: 60, Capacidad: 50
     */
    public void generarReporteAlertasCapacidad() {
        for (Atraccion atraccion : atracciones) {
            if (atraccion.getVisitantesPorDia() > atraccion.getCapacidadMaxima()) {
                System.out.println(atraccion.getNombre() + " ha superado su capacidad maxima. Visitantes: " + atraccion.getVisitantesPorDia() + ", Capacidad: " + atraccion.getCapacidadMaxima());
            }
        }
    }
    public void agregarSimulador(String nombre, String zona, int capacidad, int edadMin, double precio, int estaciones, boolean anteojos) {
    atracciones.add(new Simulador(estaciones, anteojos, nombre, zona, capacidad, edadMin, 0, precio));
}

public void agregarJuegoInfantil(String nombre, String zona, int capacidad, int edadMin, double precio, int edadMax, boolean supervision) {
    atracciones.add(new JuegoInfantil(edadMax, supervision, nombre, zona, capacidad, edadMin, 0, precio));
}

public void agregarEspectaculo(String nombre, String zona, int capacidad, int edadMin, double precio, int duracion, boolean materialPeligroso) {
    atracciones.add(new Espectaculo("Pirotecnico", duracion, nombre, zona, capacidad, edadMin, 0, precio)); // Corregido tipoEspectaculo
}
}