public class Entrada {
    private Funcion funcion;
    private Asiento asiento;

    public Entrada(Funcion funcion, Asiento asiento) {
        this.funcion = funcion;
        this.asiento = asiento;
    }

    public double getCosto() {
        return funcion.calcularCostoTotal() + asiento.obtenerCosto();
    }

    @Override
    public String toString() {
        return "Entrada para la función " + funcion + " con asiento " + asiento + " por " + getCosto();
    }
}