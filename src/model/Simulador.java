package model;

public class Simulador extends Atraccion implements Clasificable {
    private int numeroEstaciones;
    private boolean requiereAnteojos;

    public Simulador(int numeroEstaciones, boolean requiereAnteojos, String nombre, String zonaUbicacion,
            int capacidadMaxima,
            int edadMinimaAnios, int visitantesPorDia, double precioEntrada) {
        super(nombre, zonaUbicacion, capacidadMaxima, edadMinimaAnios, visitantesPorDia, precioEntrada);
        this.numeroEstaciones = numeroEstaciones;
        this.requiereAnteojos = requiereAnteojos;
    }

    @Override
    public double calcularIngresoDiario() {
        double ingresoDiario = visitantesPorDia * precioEntrada;
        if (requiereAnteojos == true) {
            return ingresoDiario;
        } else {
            return ingresoDiario * 0.9;
        }
    }

    @Override
    public boolean requiereMantenimiento() {

        if (visitantesPorDia > capacidadMaxima || numeroEstaciones > 20) {
            return true;
        } else {
            return false;
        }
    }

    public String getNivelRiesgo() {
        if (requiereAnteojos == true && numeroEstaciones > 20) {
            return "Alto";
        } else if (requiereAnteojos == true || numeroEstaciones > 20) {
            return "Medio";
        } else {
            return "Bajo";
        }
    }

    public String toString() {
        return super.toString() +
                "\nNumero de estaciones: " + numeroEstaciones +
                "\nRequiere anteojos: " + requiereAnteojos +
                "\nNivel de riesgo: " + getNivelRiesgo();
    }
}