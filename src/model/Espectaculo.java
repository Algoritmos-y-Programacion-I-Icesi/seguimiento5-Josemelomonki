package model;

public class Espectaculo extends Atraccion implements Clasificable {
    private int duracionMinutos;
    private boolean materialPeligroso;

    public Espectaculo(String tipoEspectaculo, int duracionMinutos, boolean materialPeligroso, String nombre,
            String zonaUbicacion,
            int capacidadMaxima, int edadMinimaAnios, int visitantesPorDia, double precioEntrada) {
        super(nombre, zonaUbicacion, capacidadMaxima, edadMinimaAnios, visitantesPorDia, precioEntrada);
        this.duracionMinutos = duracionMinutos;
        this.materialPeligroso = materialPeligroso;
    }

    @Override
    public double calcularIngresoDiario() {
        double ingresoDiario = visitantesPorDia * precioEntrada;
        if (materialPeligroso == true) {
            return ingresoDiario * 1.2;
        } else {
            return ingresoDiario;
        }
    }

    @Override
    public boolean requiereMantenimiento() {
        if (materialPeligroso == true || duracionMinutos > 60) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getNivelRiesgo() {
        if (materialPeligroso == true) {
            return "Alto";
        } else if (duracionMinutos > 60) {
            return "Medio";
        } else {
            return "Bajo";
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nMaterial peligroso: " + materialPeligroso +
                "\nDuracion: " + duracionMinutos + " minutos";
    }

}
