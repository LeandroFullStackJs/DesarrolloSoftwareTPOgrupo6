public class RecargoPorDuracion extends AsientoDecorator {
    private int duracion;

    public RecargoPorDuracion(Asiento asiento, int duracion) {
        super(asiento);
        this.duracion = duracion;
    }

    @Override
    public double obtenerCosto() {
        return super.obtenerCosto() + (duracion * 5); // Ejemplo: 5 unidades por minuto
    }
}