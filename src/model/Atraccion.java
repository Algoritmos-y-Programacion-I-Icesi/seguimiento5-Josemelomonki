package model;

/**
 * Clase que representa una atraccion del parque MagicWorld.
 */
public abstract class Atraccion {

    protected String nombre;
    protected String zonaUbicacion;
    protected int capacidadMaxima;
    protected int edadMinimaAnios;
    protected int visitantesPorDia;
    protected double precioEntrada;

    /**
     * Constructor de la clase Atraccion
     */
    public Atraccion(String nombre, String zonaUbicacion, int capacidadMaxima,
                     int edadMinimaAnios, int visitantesPorDia, double precioEntrada) {
        this.nombre = nombre;
        this.zonaUbicacion = zonaUbicacion;
        this.capacidadMaxima = capacidadMaxima;
        this.edadMinimaAnios = edadMinimaAnios;
        this.visitantesPorDia = visitantesPorDia;
        this.precioEntrada = precioEntrada;
    }

     /**
     * Retorna una representacion en texto de la información de la atracción
     */
    @Override
    public String toString() {
        return "--------------------------------------------" +
                "\nNombre        : " + nombre +
                "\nZona          : " + zonaUbicacion +
                "\nCapacidad max : " + capacidadMaxima + " personas" +
                "\nEdad minima   : " + edadMinimaAnios + " años" +
                "\nVisitantes hoy: " + visitantesPorDia +
                "\nPrecio entrada: $" + String.format("%,.2f", precioEntrada) +
                "\nIngreso diario: $" + String.format("%,.2f", calcularIngresoDiario());
    }

    public abstract double calcularIngresoDiario();

    public abstract boolean requiereMantenimiento();


    // Getters
    public void setVisitantesPorDia(int visitantes){visitantesPorDia = visitantes;}
    public String getNombre() { return nombre; }
    public String getZonaUbicacion() { return zonaUbicacion; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public int getEdadMinimaAnios() { return edadMinimaAnios; }
    public int getVisitantesPorDia() { return visitantesPorDia; }
    public double getPrecioEntrada() { return precioEntrada; }
}
