package model;

public class JuegoInfantil extends Atraccion {
    private int edadMaxima;
    private boolean supervision;

    public JuegoInfantil(int edadMaxima, boolean supervision, String nombre, String zonaUbicacion, int capacidadMaxima,
            int edadMinimaAnios, int visitantesPorDia, double precioEntrada) {
        super(nombre, zonaUbicacion, capacidadMaxima, edadMinimaAnios, visitantesPorDia, precioEntrada);
        this.edadMaxima = edadMaxima;
        this.supervision = supervision;
    }

    @Override
    public double calcularIngresoDiario() {
        double ingresoDiario = visitantesPorDia * precioEntrada;
        if (supervision == true) {
            return ingresoDiario + (visitantesPorDia * 50000);
        } else {
            return ingresoDiario;
        }
    }

    @Override
    public boolean requiereMantenimiento() {
        if (visitantesPorDia > capacidadMaxima || supervision == false) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nEdad maxima: " + edadMaxima +
                "\nRequiere supervision: " + supervision;
    }
}