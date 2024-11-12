public class Entrada {
    private Funcion funcion;
    private Asiento asiento;

    public Entrada(Funcion funcion, Asiento asiento) {
        this.funcion = funcion;
        this.asiento = asiento;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
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